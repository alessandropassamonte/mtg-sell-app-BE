package com.mtgsell.mtgsellapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
    private String setCode;
    private String setUri;
    private String setIcon;
    private Date releaseDate;
    private String setSearchUri;
    private String scryfallSetUri;

    private String cardMarketName;

}
