package com.is1di.remotecoffeemachine.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTimestamp;
    private LocalDateTime startTimestamp;
    private LocalDateTime closeTimestamp;
    private CoffeeStatusEntity status;
    @Embedded
    private CoffeeEntity coffee;
}
