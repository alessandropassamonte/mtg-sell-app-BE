package com.mtgsell.mtgsellapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String cardId;
    private Long oracleId;
    private Long tcgplayerId;
    private Long cardmarketId;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedName;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedText;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedTypeLine;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String flavorText;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String lang;
    private Date releasedAt;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String uri;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String scryfallUri;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String layout;
    private Boolean highresImage;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageStatus;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String png;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String manaCost;
    private Integer cmc;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String typeLine;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String oracleText;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String power;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String toughness;
    private Boolean reserved;
    private Boolean foil;
    private Boolean nonfoil;
    private Boolean oversized;
    private Boolean promo;
    private Boolean reprint;
    private Boolean variation;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String rulingsUri;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printsSearchUri;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String collectorNumber;
    private Boolean digital;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String rarity;
    private Long cardBackId;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String artist;
    private Long illustrationId;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String borderColor;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String frame;
    private Boolean fullArt;
    private Boolean textless;
    private Boolean booster;
    private Boolean storySpotlight;
    private Integer edhrecRank;
    private BigDecimal price;
    private BigDecimal priceFoil;
    private String priceCM;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String typeLineFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String manaCostFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String nameFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String powerFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String toughnessFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedNameFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedTypeLineFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedTextFace1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String flavorTextFace1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String typeLineFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String manaCostFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String nameFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String powerFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String toughnessFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedNameFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedTypeLineFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String printedTextFace2;
    @Lob
    @Column(columnDefinition = "TEXT")
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


    @OneToMany(mappedBy="card")
    @JsonIgnore
    private List<UserCard> usersCards;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", oracleId=" + oracleId +
                ", tcgplayerId=" + tcgplayerId +
                ", cardmarketId=" + cardmarketId +
                ", name='" + name + '\'' +
                ", printedName='" + printedName + '\'' +
                ", printedText='" + printedText + '\'' +
                ", printedTypeLine='" + printedTypeLine + '\'' +
                ", flavorText='" + flavorText + '\'' +
                ", lang='" + lang + '\'' +
                ", releasedAt=" + releasedAt +
                ", uri='" + uri + '\'' +
                ", scryfallUri='" + scryfallUri + '\'' +
                ", layout='" + layout + '\'' +
                ", highresImage=" + highresImage +
                ", imageStatus='" + imageStatus + '\'' +
                ", png='" + png + '\'' +
                ", manaCost='" + manaCost + '\'' +
                ", cmc=" + cmc +
                ", typeLine='" + typeLine + '\'' +
                ", oracleText='" + oracleText + '\'' +
                ", power='" + power + '\'' +
                ", toughness='" + toughness + '\'' +
                ", reserved=" + reserved +
                ", foil=" + foil +
                ", nonfoil=" + nonfoil +
                ", oversized=" + oversized +
                ", promo=" + promo +
                ", reprint=" + reprint +
                ", variation=" + variation +
                ", rulingsUri='" + rulingsUri + '\'' +
                ", printsSearchUri='" + printsSearchUri + '\'' +
                ", collectorNumber='" + collectorNumber + '\'' +
                ", digital=" + digital +
                ", rarity='" + rarity + '\'' +
                ", cardBackId=" + cardBackId +
                ", artist='" + artist + '\'' +
                ", illustrationId=" + illustrationId +
                ", borderColor='" + borderColor + '\'' +
                ", frame='" + frame + '\'' +
                ", fullArt=" + fullArt +
                ", textless=" + textless +
                ", booster=" + booster +
                ", storySpotlight=" + storySpotlight +
                ", edhrecRank=" + edhrecRank +
                ", price=" + price +
                ", priceFoil=" + priceFoil +
                ", typeLineFace1='" + typeLineFace1 + '\'' +
                ", manaCostFace1='" + manaCostFace1 + '\'' +
                ", nameFace1='" + nameFace1 + '\'' +
                ", powerFace1='" + powerFace1 + '\'' +
                ", toughnessFace1='" + toughnessFace1 + '\'' +
                ", imageFace1='" + imageFace1 + '\'' +
                ", printedNameFace1='" + printedNameFace1 + '\'' +
                ", printedTypeLineFace1='" + printedTypeLineFace1 + '\'' +
                ", printedTextFace1='" + printedTextFace1 + '\'' +
                ", flavorTextFace1='" + flavorTextFace1 + '\'' +
                ", typeLineFace2='" + typeLineFace2 + '\'' +
                ", manaCostFace2='" + manaCostFace2 + '\'' +
                ", nameFace2='" + nameFace2 + '\'' +
                ", powerFace2='" + powerFace2 + '\'' +
                ", toughnessFace2='" + toughnessFace2 + '\'' +
                ", imageFace2='" + imageFace2 + '\'' +
                ", printedNameFace2='" + printedNameFace2 + '\'' +
                ", printedTypeLineFace2='" + printedTypeLineFace2 + '\'' +
                ", printedTextFace2='" + printedTextFace2 + '\'' +
                ", flavorTextFace2='" + flavorTextFace2 + '\'' +
                ", colors=" + colors +
                ", colorIdentities=" + colorIdentities +
                ", editions=" + editions +
                ", legalities=" + legalities +
                '}';
    }
}