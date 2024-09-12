package com.shohochori.bondhu.pendingrequest;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pending_request")
public class PendingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private String type;

    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime fulfilledAt;

    private Double latitude;
    private Double longitude;

    private Integer assistantId;

    private String status;

    @Column(columnDefinition = "SMALLINT DEFAULT 0")
    private Integer notified;
}
