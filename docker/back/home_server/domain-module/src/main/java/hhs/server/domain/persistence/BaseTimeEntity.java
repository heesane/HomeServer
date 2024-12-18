package hhs.server.domain.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@ToString
@Setter
public abstract class BaseTimeEntity {

  @CreatedDate
  @Column(name = "registered_at", updatable = false)
  private LocalDateTime registeredAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = true)
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at", nullable = true)
  private LocalDateTime deletedAt;
}
