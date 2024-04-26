package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Edition;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditionRepository  extends JpaRepository<Edition, Long> {

    Optional<Edition> findBySetId(String setId);
}
