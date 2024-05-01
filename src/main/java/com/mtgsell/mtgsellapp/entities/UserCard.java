package com.mtgsell.mtgsellapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity
@Table(name = "users_cards")
@Data
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private Boolean inVendita;

    private Boolean foil;

    private Boolean attivo;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Override
    public String toString() {
        return "UserCard{" +
                "id=" + id +
                ", date=" + date +
                ", inVendita=" + inVendita +
                ", card=" + card +
                '}';
    }
}
