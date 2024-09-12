package com.shohochori.bondhu.assistant;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "assistant")
public class Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assistantId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime dob;

    private String gender;

    private String profilePicture;

    private String number;

    private String address;

    @CurrentTimestamp
    private LocalDateTime created;

    private String idDocument;

    private double latitude;
    private double longitude;
}

