package com.mtgsell.mtgsellapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardId;
    private Long oracleId;
    private Long tcgplayerId;
    private Long cardmarketId;
    private String name;
    private String lang;
    private Date releasedAt;
    private String uri;
    private String scryfallUri;
    private String layout;
    private Boolean highresImage;
    private String imageStatus;
    private String png;
    private String manaCost;
    private Integer cmc;
    private String typeLine;
    private String oracleText;
    private String power;
    private String toughness;
    private Boolean reserved;
    private Boolean foil;
    private Boolean nonfoil;
    private Boolean oversized;
    private Boolean promo;
    private Boolean reprint;
    private Boolean variation;
    private String rulingsUri;
    private String printsSearchUri;
    private String collectorNumber;
    private Boolean digital;
    private String rarity;
    private Long cardBackId;
    private String artist;
    private Long illustrationId;
    private String borderColor;
    private String frame;
    private Boolean fullArt;
    private Boolean textless;
    private Boolean booster;
    private Boolean storySpotlight;
    private Integer edhrecRank;
    private BigDecimal eur;

    @ManyToMany
    @JoinTable(name = "card_colors",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors;

    @ManyToMany
    @JoinTable(name = "card_colors_identities",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colorIdentities;

    @ManyToMany
    @JoinTable(name = "card_edition",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "edition_id"))
    private List<Edition> editions;

    @ManyToMany
    @JoinTable(name = "card_legalities",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "legality_id"))
    private List<Legality> legalities;

}