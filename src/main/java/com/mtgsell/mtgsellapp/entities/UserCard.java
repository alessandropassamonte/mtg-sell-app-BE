package com.mtgsell.mtgsellapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "users_cards")
@Data
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;



}