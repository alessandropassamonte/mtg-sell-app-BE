package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.UserCard;
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
public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    @Query("SELECT uc FROM UserCard uc WHERE uc.users = :user AND uc.attivo = true ORDER BY uc.date desc ")
    Page<UserCard> findAllByUser(@Param("user") UserEntity user, Pageable pageable);


    @Query("SELECT uc.card FROM UserCard uc join uc.card c " +
            " WHERE uc.users = :user AND" +
            " (c.name LIKE %:search% OR c.printedName LIKE %:search%) AND " +
            "((c.typeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand AND c.typeLine NOT LIKE :token AND c.typeLine NOT LIKE 'Card // Card') OR " +
            "(c.printedTypeLine IS NOT NULL AND c.typeLine NOT LIKE :basicLand))")
    Optional<List<Card>> findAutocomplete(@Param("basicLand") String basicLand, @Param("token") String token, @Param("search") String search, @Param("user") UserEntity user);
}
