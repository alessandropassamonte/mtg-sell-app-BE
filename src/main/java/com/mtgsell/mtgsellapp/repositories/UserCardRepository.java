package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long> {
}
