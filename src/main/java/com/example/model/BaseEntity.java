package com.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

  @Column(updatable = false)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}