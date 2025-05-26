package com.example.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private LocalDateTime updatedAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(updatable = false)
    private String updatedBy;

}
