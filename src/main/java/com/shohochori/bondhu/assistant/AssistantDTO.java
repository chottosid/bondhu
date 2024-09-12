package com.shohochori.bondhu.assistant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssistantDTO {
    private String name;
    private String email;
    private String password;
    private LocalDateTime dob;
    private String gender;
    private String profilePicture;
    private String number;
    private String address;
    private String idDocument;
    private double latitude;
    private double longitude;
}