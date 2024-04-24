package com.mtgsell.mtgsellapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "edition")
@Data
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String setId;
    private String setName;
    private String setType;
    private String setUri;
    private String setSearchUri;
    private String scryfallSetUri;

}
