package com.enzulode.dao.repository;

import com.enzulode.dao.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

  Optional<Card> findByCardId(UUID cardId);

  @Modifying
  @Query(value = "WITH deleted_card AS (delete from card c where c.id = :id returning *) INSERT INTO event (card_id, type, username, at) SELECT dc.card_id, 'END', dc.username, now() from deleted_card dc WHERE EXISTS(select * from event ie WHERE ie.card_id = dc.card_id AND type = 'BEGIN' ORDER BY ie.at DESC LIMIT 1)", nativeQuery = true)
  void deleteAndEndIfStartedById(@Param("id") Long id);
}
