package com.shohochori.bondhu.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantService {
    @Autowired
    private AssistantRepository assistantRepository;

    public Assistant login(String email, String password) {
        Assistant assistant = assistantRepository.findByEmail(email);
        if (assistant != null && assistant.getPassword().equals(password)) {
            return assistant;
        }
        return null;
    }

    public void register(Assistant assistant) {
        assistantRepository.save(assistant);
    }

    public List<Assistant> getNearbyAssistants(double latitude, double longitude) {
        return assistantRepository.findAssistantsWithinDistance(latitude, longitude, 1000);
    }
}
