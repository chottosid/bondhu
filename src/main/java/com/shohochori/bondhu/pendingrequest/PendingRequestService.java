package com.shohochori.bondhu.pendingrequest;

import com.shohochori.bondhu.assistant.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class PendingRequestService {
    @Autowired
    private PendingRequestRepository pendingRequestRepository;
    public void save(RequestDTO requestDTO) {
        PendingRequest pendingRequest = new PendingRequest();
        pendingRequest.setUserId(requestDTO.getUserId());
        pendingRequest.setType(requestDTO.getType());
        pendingRequest.setDescription(requestDTO.getDescription());
        pendingRequest.setLongitude(requestDTO.getLongitude());
        pendingRequest.setLatitude(requestDTO.getLatitude());
        pendingRequest.setStatus("pending");
        pendingRequestRepository.save(pendingRequest);
    }
    public void save(PendingRequest pendingRequest) {
        pendingRequestRepository.save(pendingRequest);
    }
    public List<PendingRequest> getPendingRequests(Double latitude, Double longitude) {
        return pendingRequestRepository.findPendingRequestsWithinDistance(latitude, longitude, 3000);
    }

    public void remove(Integer requestId) {
        pendingRequestRepository.deleteById(requestId);
    }

    public Optional<PendingRequest> getPendingRequest(Integer requestId) {
        return pendingRequestRepository.findById(requestId);
    }

    public boolean acceptRequest(Integer requestId, Integer assistantId) {
        PendingRequest pendingRequest = pendingRequestRepository.findById(requestId).orElse(null);
        if(pendingRequest == null || pendingRequest.getAssistantId() != null) {
            return false;
        }
        pendingRequest.setAssistantId(assistantId);
        pendingRequest.setUpdatedAt(LocalDateTime.now());
        pendingRequest.setStatus("accepted");
        pendingRequestRepository.save(pendingRequest);
        return true;
    }


    public List<PendingRequest> findRequestsByUserId(Integer userId) {
        return pendingRequestRepository.findByUserId(userId);
    }
}
