package com.shohochori.bondhu.assistant;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {
    private int userId;
    private String type;
    private String description;
    private Double latitude;
    private Double longitude;
}
