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

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    @Query("SELECT uc FROM UserCard uc WHERE uc.users = :user AND uc.attivo = true")
    Page<UserCard> findAllByUser(@Param("user") UserEntity user, Pageable pageable);
}
