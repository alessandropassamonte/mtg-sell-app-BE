package com.mtgsell.mtgsellapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "legalities")
@Data
public class Legality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String format;
    private String legality;
}
