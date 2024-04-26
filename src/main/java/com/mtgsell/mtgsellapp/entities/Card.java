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

    @Lob
    private String cardId;
    private Long oracleId;
    private Long tcgplayerId;
    private Long cardmarketId;
    @Lob
    private String name;
    @Lob
    private String printedName;
    @Lob
    private String printedText;
    @Lob
    private String printedTypeLine;
    @Lob
    private String flavorText;
    @Lob
    private String lang;
    private Date releasedAt;
    @Lob
    private String uri;
    @Lob
    private String scryfallUri;
    @Lob
    private String layout;
    private Boolean highresImage;
    @Lob
    private String imageStatus;
    @Lob
    private String png;
    @Lob
    private String manaCost;
    private Integer cmc;
    @Lob
    private String typeLine;
    @Lob
    private String oracleText;
    @Lob
    private String power;
    @Lob
    private String toughness;
    private Boolean reserved;
    private Boolean foil;
    private Boolean nonfoil;
    private Boolean oversized;
    private Boolean promo;
    private Boolean reprint;
    private Boolean variation;
    @Lob
    private String rulingsUri;
    @Lob
    private String printsSearchUri;
    @Lob
    private String collectorNumber;
    private Boolean digital;
    @Lob
    private String rarity;
    private Long cardBackId;
    @Lob
    private String artist;
    private Long illustrationId;
    @Lob
    private String borderColor;
    @Lob
    private String frame;
    private Boolean fullArt;
    private Boolean textless;
    private Boolean booster;
    private Boolean storySpotlight;
    private Integer edhrecRank;
    private BigDecimal price;
    private BigDecimal priceFoil;

    @Lob
    private String typeLineFace1;
    @Lob
    private String manaCostFace1;
    @Lob
    private String nameFace1;
    @Lob
    private String powerFace1;
    @Lob
    private String toughnessFace1;
    @Lob
    private String imageFace1;
    @Lob
    private String printedNameFace1;
    @Lob
    private String printedTypeLineFace1;
    @Lob
    private String printedTextFace1;
    @Lob
    private String flavorTextFace1;

    @Lob
    private String typeLineFace2;
    @Lob
    private String manaCostFace2;
    @Lob
    private String nameFace2;
    @Lob
    private String powerFace2;
    @Lob
    private String toughnessFace2;
    @Lob
    private String imageFace2;
    @Lob
    private String printedNameFace2;
    @Lob
    private String printedTypeLineFace2;
    @Lob
    private String printedTextFace2;
    @Lob
    private String flavorTextFace2;





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