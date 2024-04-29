package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.Edition;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    Optional<Card> findByCardId(String cardId);

    @Query("SELECT c FROM Card c JOIN c.usersCards uc WHERE uc.users = :user")
    Page<Card> findAllByUser(@Param("user") UserEntity user, Pageable pageable);

}
