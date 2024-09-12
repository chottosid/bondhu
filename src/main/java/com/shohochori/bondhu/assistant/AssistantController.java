package com.shohochori.bondhu.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assistant")
public class AssistantController {
    @Autowired
    private AssistantService assistantService;

    @PostMapping("/login")
    public ResponseEntity<Assistant> login(@RequestBody LoginDTO loginDTO) {
        Assistant assistant = assistantService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if(assistant == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(assistant);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AssistantDTO assistantDTO) {
        if (assistantService.login(assistantDTO.getEmail(), assistantDTO.getPassword()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        Assistant assistant = new Assistant();
        assistant.setName(assistantDTO.getName());
        assistant.setEmail(assistantDTO.getEmail());
        assistant.setPassword(assistantDTO.getPassword());
        assistant.setDob(assistantDTO.getDob());
        assistant.setGender(assistantDTO.getGender());
        assistant.setProfilePicture(assistantDTO.getProfilePicture());
        assistant.setNumber(assistantDTO.getNumber());
        assistant.setAddress(assistantDTO.getAddress());
        assistant.setIdDocument(assistantDTO.getIdDocument());
        assistant.setLatitude(assistantDTO.getLatitude());
        assistant.setLongitude(assistantDTO.getLongitude());

        assistantService.register(assistant);
        return ResponseEntity.ok("Registered successfully");
    }
}