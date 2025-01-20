package com.enzulode.dao.repository;

import com.enzulode.dao.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

  Optional<Card> findByCardId(UUID cardId);
}
