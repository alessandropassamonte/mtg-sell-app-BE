package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.Edition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE " +
            "(c.typeLine IS NOT NULL AND c.typeLine NOT LIKE %:basicLand% AND c.typeLine NOT LIKE %:token%) OR " +
            "(c.printedTypeLine IS NOT NULL AND c.printedTypeLine NOT LIKE %:terraBase%)")
    Page<Card> findAllCards(String basicLand, String terraBase, String token, Pageable pageable);

    @Query("SELECT c FROM Card c WHERE (c.name LIKE %:search% or c.printedName LIKE %:search%) AND ( " +
            "(c.typeLine IS NOT NULL AND c.typeLine NOT LIKE %:basicLand% AND c.typeLine NOT LIKE %:token%) OR " +
            "(c.printedTypeLine IS NOT NULL AND c.printedTypeLine NOT LIKE %:terraBase%) )  ")
    Page<Card> findAllCardsByName(String basicLand, String terraBase, String token, String search, Pageable pageable);
}
