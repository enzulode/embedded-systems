package com.enzulode.dao.entity;

import com.enzulode.dto.util.EventType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private UUID cardId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private EventType type;

  @Column(nullable = false)
  private LocalDateTime at;

  public Event(UUID cardId, String username, EventType type) {
    this.cardId = cardId;
    this.username = username;
    this.type = type;
  }

  @PrePersist
  private void insertAt() {
    at = LocalDateTime.now();
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Event event = (Event) o;
    return getId() != null && Objects.equals(getId(), event.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
