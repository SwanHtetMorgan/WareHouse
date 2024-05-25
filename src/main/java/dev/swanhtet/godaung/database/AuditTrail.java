package dev.swanhtet.godaung.database;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditTrail {

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at",nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  private void setCreatedAt() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }
}
