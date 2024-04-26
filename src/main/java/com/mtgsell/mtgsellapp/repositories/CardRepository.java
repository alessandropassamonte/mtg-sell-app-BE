package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
