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

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE " +
            "(c.typeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand AND c.typeLine NOT LIKE :token AND c.typeLine NOT LIKE 'Card // Card') OR " +
            "(c.printedTypeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand)")
    Page<Card> findAllCards(@Param("basicLand") String basicLand, @Param("token") String token, Pageable pageable);

    @Query("SELECT c FROM Card c WHERE " +
            "(c.name LIKE %:search% OR c.printedName LIKE %:search%) AND " +
            "((c.typeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand AND c.typeLine NOT LIKE :token AND c.typeLine NOT LIKE 'Card // Card') OR " +
            "(c.printedTypeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand))")
    Page<Card> findAllCardsByName(@Param("basicLand") String basicLand, @Param("token") String token, @Param("search") String search, Pageable pageable);


    Optional<Card> findByCardId(String cardId);

    @Query("SELECT c FROM Card c JOIN c.usersCards uc WHERE uc.users = :user")
    Page<Card> findAllByUser(@Param("user") UserEntity user, Pageable pageable);


    @Query("SELECT c FROM Card c WHERE " +
            "(c.name LIKE %:search% OR c.printedName LIKE %:search%) AND " +
            "((c.typeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand AND c.typeLine NOT LIKE :token AND c.typeLine NOT LIKE 'Card // Card') OR " +
            "(c.printedTypeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand))")
    Optional<List<Card>> findAutocomplete(@Param("basicLand") String basicLand, @Param("token") String token, @Param("search") String search);

    @Query("SELECT c FROM Card c where c.cardId = :cardId" )
    Optional<Card> findBySetIt(@Param("cardId") String cardId);

}
