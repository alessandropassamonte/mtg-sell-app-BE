package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Edition;
import com.mtgsell.mtgsellapp.entities.Legality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalityRepository extends JpaRepository<Legality, Long> {

    Optional<Legality> findByLegality(String legality);
}
