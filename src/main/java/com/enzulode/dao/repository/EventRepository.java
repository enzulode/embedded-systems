package com.enzulode.dao.repository;

import com.enzulode.dao.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query(value = "SELECT e FROM Event e INNER JOIN Card c ON c.cardId = e.cardId WHERE e.type <> 'DENIED' AND e.cardId = :cardId ORDER BY e.at DESC LIMIT 1")
  Optional<Event> findLatestEventFor(@Param("cardId") UUID cardId);

  @Query(value = "SELECT e FROM Event e INNER JOIN Card c ON c.cardId = e.cardId WHERE e.type <> 'DENIED' AND c.id = :id ORDER BY e.at DESC LIMIT 1")
  Optional<Event> findLatestEventFor(@Param("id") Long id);
}
