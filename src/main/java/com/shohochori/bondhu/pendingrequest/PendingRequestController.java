package com.shohochori.bondhu.pendingrequest;

import com.shohochori.bondhu.assistant.RequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pending")
public class PendingRequestController {
    @Autowired
    private PendingRequestService pendingRequestService;


    //User will send new request
    @PostMapping("/send")
    public ResponseEntity<String> sendRequest(@RequestBody RequestDTO requestDTO) {
        pendingRequestService.save(requestDTO);
        return ResponseEntity.ok("Request sent");
    }

    //Assistant will get pending requests nearby 3000 meters of his location
    @GetMapping("/requests")
    public ResponseEntity<?> getPendingRequests(@RequestParam Double latitude, @RequestParam Double longitude) {
        System.out.println("Latitude: "+latitude+" Longitude: "+longitude);
        List<PendingRequest> pendingRequests = pendingRequestService.getPendingRequests(latitude, longitude);
        if(pendingRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pendingRequests.get(0));
    }

    //Assistant will confirm the request
    @PostMapping("/confirm")
    public ResponseEntity<?> acceptRequest(@RequestBody AssistantRequestDTO assistantRequestDTO) {
        boolean accepted = pendingRequestService.acceptRequest(assistantRequestDTO.getRequestId(), assistantRequestDTO.getAssistantId());
        if(accepted) {
            return ResponseEntity.ok("Request accepted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request already accepted");
    }

    //User will receive the confirmation of his request
    @GetMapping("/notification/{userId}")
    public ResponseEntity<?> getNotification(@PathVariable Integer userId) {
        List<PendingRequest> pendingRequests = pendingRequestService.findRequestsByUserId(userId);
        if(pendingRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        List<PendingRequest> acceptedRequests = new ArrayList<>();
        for(PendingRequest pendingRequest : pendingRequests) {
            if(pendingRequest.getStatus().equals("accepted") && pendingRequest.getNotified()==0) {
                acceptedRequests.add(pendingRequest);
                pendingRequest.setNotified(1);
                pendingRequestService.save(pendingRequest);
            }
        }
        return ResponseEntity.ok(acceptedRequests);
    }

    @GetMapping("/check/{userId}")
    public ResponseEntity<?> checkRequest(@PathVariable Integer userId) {
        List<PendingRequest> pendingRequests = pendingRequestService.findRequestsByUserId(userId);
        if(pendingRequests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pendingRequests);
    }

}
