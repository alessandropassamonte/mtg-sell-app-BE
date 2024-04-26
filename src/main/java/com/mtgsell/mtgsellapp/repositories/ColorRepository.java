package com.mtgsell.mtgsellapp.repositories;

import com.mtgsell.mtgsellapp.entities.Color;
import com.mtgsell.mtgsellapp.entities.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findByColor(String color);
}
