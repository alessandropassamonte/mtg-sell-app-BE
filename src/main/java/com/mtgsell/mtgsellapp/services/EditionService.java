package com.mtgsell.mtgsellapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgsell.mtgsellapp.entities.*;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import com.mtgsell.mtgsellapp.repositories.ColorRepository;
import com.mtgsell.mtgsellapp.repositories.EditionRepository;
import com.mtgsell.mtgsellapp.repositories.LegalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EditionService {

    @Autowired
    EditionRepository editionRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    LegalityRepository legalityRepository;

    @Transactional
    public void saveEdition() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> entities = objectMapper.readValue(new File("C:\\progetti\\MTG SELL APP\\FE\\src\\assets\\JSON\\filtered.json"), List.class);
//
//        try{
//            for (Map<String, Object> entity : entities) {
////                System.out.println(entity.get("name").toString());
//
//                Card card = new Card();
//                //SET
//                List<Edition> editions = new ArrayList<>();
//                editions.add(editionRepository.findBySetId(entity.get("set_id").toString()).orElseThrow());
//                card.setEditions(editions);
//
//                //LEGALITY
//                List<Legality> legalitiesToAdd = new ArrayList<>();
//                Map<String, String> legalitiesMap = (Map<String, String>) entity.get("legalities");
//                List<String> legalKeys = new ArrayList<>();
//                for (Map.Entry<String, String> entry : legalitiesMap.entrySet()) {
//                    if ("legal".equals(entry.getValue())) {
//                        legalKeys.add(entry.getKey());
//                    }
//                }
//                for (String leg : legalKeys) {
//                    legalitiesToAdd.add(legalityRepository.findByLegality(leg).orElseThrow());
//                }
//                card.setLegalities(legalitiesToAdd);
//
//
//                //COLORI
//                List<Color> color = new ArrayList<>();
//                List<Color> colorIdentities = new ArrayList<>();
//                if(Objects.nonNull(entity.get("color"))){
//                    List<String> colorList = (List<String>) entity.get("colors");
//                    if (colorList.isEmpty()) {
//                        color.add(colorRepository.findByColor("N").orElseThrow());
//                    } else {
//                        for (String colorItem : colorList) {
//                            color.add(colorRepository.findByColor(colorItem).orElseThrow());
//                        }
//                    }
//                    card.setColors(color);
//                }
//                if(Objects.nonNull(entity.get("color_identity"))){
//                    List<String> identityList = (List<String>) entity.get("color_identity");
//                    if (identityList.isEmpty()) {
//                        colorIdentities.add(colorRepository.findByColor("N").orElseThrow());
//                    } else {
//                        for (String colorItem : identityList) {
//                            colorIdentities.add(colorRepository.findByColor(colorItem).orElseThrow());
//                        }
//                    }
//                    card.setColorIdentities(colorIdentities);
//                }
//
//                card.setCardId(entity.get("id").toString());
//                card.setName(entity.get("name").toString());
//                card.setPrintedName(Objects.nonNull(entity.get("printed_name")) ? entity.get("printed_name").toString() : null);
//                card.setPrintedText(Objects.nonNull(entity.get("printed_text")) ? entity.get("printed_text").toString() : null);
//                card.setLang(entity.get("lang").toString());
//                Date date = dateFormat.parse(entity.get("released_at").toString());
//                card.setReleasedAt(date);
//                card.setUri(entity.get("uri").toString());
//                card.setScryfallUri(entity.get("scryfall_uri").toString());
//                card.setLayout(entity.get("layout").toString());
//                card.setHighresImage((Boolean) entity.get("highres_image"));
//                card.setImageStatus(entity.get("image_status").toString());
//
//                if(Objects.nonNull(entity.get("image_uris"))){
//                    Map<String, String> image = (Map<String, String>) entity.get("image_uris");
//                    String pngImage = "";
//                    for (Map.Entry<String, String> entry : image.entrySet()) {
//                        if ("png".equals(entry.getKey())) {
//                            pngImage = entry.getValue();
//                        }
//                    }
//                    card.setPng(pngImage);
//                }
//
//                card.setManaCost(Objects.nonNull(entity.get("mana_cost")) ? entity.get("mana_cost").toString(): null);
//                if(Objects.nonNull(entity.get("cmc"))){
//                    Double cmcDouble = (Double) entity.get("cmc");
//                    Integer cmcInteger = cmcDouble.intValue(); // Converti il Double in Integer
//                    card.setCmc(cmcInteger);
//                }
//
//                card.setTypeLine(Objects.nonNull(entity.get("type_line")) ? entity.get("type_line").toString() : null);
//                card.setOracleText(Objects.nonNull(entity.get("oracle_text")) ? entity.get("oracle_text").toString() : null);
//                card.setPower(Objects.nonNull(entity.get("power")) ? entity.get("power").toString() : null);
//                card.setToughness(Objects.nonNull(entity.get("toughness")) ? entity.get("toughness").toString() : null);
//                card.setReserved((Boolean) entity.get("reserved"));
//                card.setFoil((Boolean) entity.get("foil"));
//                card.setNonfoil((Boolean) entity.get("nonfoil"));
//                card.setOversized((Boolean) entity.get("oversized"));
//
//                card.setPromo((Boolean) entity.get("promo"));
//                card.setReprint((Boolean) entity.get("reprint"));
//                card.setVariation((Boolean) entity.get("variation"));
//                card.setRulingsUri(entity.get("rulings_uri").toString());
//                card.setPrintsSearchUri(entity.get("prints_search_uri").toString());
//                card.setCollectorNumber(entity.get("collector_number").toString());
//                card.setDigital((Boolean)entity.get("digital"));
//                card.setRarity(entity.get("rarity").toString());
//                card.setFlavorText(Objects.nonNull(entity.get("flavor_text")) ? entity.get("flavor_text").toString() : null);
//                card.setArtist(entity.get("artist").toString());
//                card.setBorderColor(entity.get("border_color").toString());
//
//                card.setFrame(entity.get("frame").toString());
//                card.setFullArt((Boolean)entity.get("full_art"));
//                card.setTextless((Boolean)entity.get("textless"));
//                card.setBooster((Boolean)entity.get("booster"));
//                card.setStorySpotlight((Boolean)entity.get("story_spotlight"));
//                card.setEdhrecRank((Integer)entity.get("edhrec_rank"));
//                Map<String, String> prices = (Map<String, String>) entity.get("prices");
//                BigDecimal price = null;
//                BigDecimal priceFoil = null;
//                for (Map.Entry<String, String> entry : prices.entrySet()) {
//                    if ("eur".equals(entry.getKey())) {
//                        price = Objects.nonNull(entry.getValue()) ? new BigDecimal(entry.getValue()) : null;
//                    }
//                    if ("eur_foil".equals(entry.getKey())) {
//                        priceFoil = Objects.nonNull(entry.getValue()) ? new BigDecimal(entry.getValue()) : null;
//                    }
//                }
//                card.setPrice(price);
//                card.setPriceFoil(priceFoil);
//
//            }
//            System.out.println("ok");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Transactional
    public void readAsyncFile() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture.supplyAsync(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\progetti\\MTG SELL APP\\FE\\src\\assets\\JSON\\filtered.json")))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, executor).thenAcceptAsync(jsonString -> {
            if (jsonString != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<Map<String, Object>> entities = objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {
                    });
                    this.createCard(entities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        }).thenRun(executor::shutdown);
    }

    @Transactional
    public void createCard(List<Map<String, Object>> entities) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Card> cardToSave = new ArrayList<>();
        try {
            int indexCard = 0;
            for (Map<String, Object> entity : entities) {
                Card card = new Card();
                //SET
                List<Edition> editions = new ArrayList<>();
                editions.add(editionRepository.findBySetId(entity.get("set_id").toString()).orElseThrow());
                card.setEditions(editions);
                //LEGALITY
                List<Legality> legalitiesToAdd = new ArrayList<>();
                Map<String, String> legalitiesMap = (Map<String, String>) entity.get("legalities");
                List<String> legalKeys = new ArrayList<>();
                for (Map.Entry<String, String> entry : legalitiesMap.entrySet()) {
                    if ("legal".equals(entry.getValue())) {
                        legalKeys.add(entry.getKey());
                    }
                }
                for (String leg : legalKeys) {
                    legalitiesToAdd.add(legalityRepository.findByLegality(leg).orElseThrow());
                }
                card.setLegalities(legalitiesToAdd);
                //COLORI
                List<Color> color = new ArrayList<>();
                List<Color> colorIdentities = new ArrayList<>();
                if (Objects.nonNull(entity.get("color"))) {
                    List<String> colorList = (List<String>) entity.get("colors");
                    if (colorList.isEmpty()) {
                        color.add(colorRepository.findByColor("N").orElseThrow());
                    } else {
                        for (String colorItem : colorList) {
                            color.add(colorRepository.findByColor(colorItem).orElseThrow());
                        }
                    }
                    card.setColors(color);
                }
                if (Objects.nonNull(entity.get("color_identity"))) {
                    List<String> identityList = (List<String>) entity.get("color_identity");
                    if (identityList.isEmpty()) {
                        colorIdentities.add(colorRepository.findByColor("N").orElseThrow());
                    } else {
                        for (String colorItem : identityList) {
                            colorIdentities.add(colorRepository.findByColor(colorItem).orElseThrow());
                        }
                    }
                    card.setColorIdentities(colorIdentities);
                }
                card.setCardId(entity.get("id").toString());
                card.setName(entity.get("name").toString());
                card.setPrintedName(Objects.nonNull(entity.get("printed_name")) ? entity.get("printed_name").toString() : null);
                card.setPrintedText(Objects.nonNull(entity.get("printed_text")) ? entity.get("printed_text").toString() : null);
                card.setLang(entity.get("lang").toString());
                Date date = dateFormat.parse(entity.get("released_at").toString());
                card.setReleasedAt(date);
                card.setUri(entity.get("uri").toString());
                card.setScryfallUri(entity.get("scryfall_uri").toString());
                card.setLayout(entity.get("layout").toString());
                card.setHighresImage((Boolean) entity.get("highres_image"));
                card.setImageStatus(entity.get("image_status").toString());
                if (Objects.nonNull(entity.get("image_uris"))) {
                    Map<String, String> image = (Map<String, String>) entity.get("image_uris");
                    String pngImage = "";
                    for (Map.Entry<String, String> entry : image.entrySet()) {
                        if ("png".equals(entry.getKey())) {
                            pngImage = entry.getValue();
                        }
                    }
                    card.setPng(pngImage);
                }
                card.setManaCost(Objects.nonNull(entity.get("mana_cost")) ? entity.get("mana_cost").toString() : null);
                if (Objects.nonNull(entity.get("cmc"))) {
                    Double cmcDouble = (Double) entity.get("cmc");
                    Integer cmcInteger = cmcDouble.intValue(); // Converti il Double in Integer
                    card.setCmc(cmcInteger);
                }
                card.setTypeLine(Objects.nonNull(entity.get("type_line")) ? entity.get("type_line").toString() : null);
                card.setPrintedTypeLine(Objects.nonNull(entity.get("printed_type_line")) ? entity.get("printed_type_line").toString() : null);
                card.setOracleText(Objects.nonNull(entity.get("oracle_text")) ? entity.get("oracle_text").toString() : null);
                card.setPower(Objects.nonNull(entity.get("power")) ? entity.get("power").toString() : null);
                card.setToughness(Objects.nonNull(entity.get("toughness")) ? entity.get("toughness").toString() : null);
                card.setReserved((Boolean) entity.get("reserved"));
                card.setFoil((Boolean) entity.get("foil"));
                card.setNonfoil((Boolean) entity.get("nonfoil"));
                card.setOversized((Boolean) entity.get("oversized"));

                card.setPromo((Boolean) entity.get("promo"));
                card.setReprint((Boolean) entity.get("reprint"));
                card.setVariation((Boolean) entity.get("variation"));
                card.setRulingsUri(entity.get("rulings_uri").toString());
                card.setPrintsSearchUri(entity.get("prints_search_uri").toString());
                card.setCollectorNumber(entity.get("collector_number").toString());
                card.setDigital((Boolean) entity.get("digital"));
                card.setRarity(entity.get("rarity").toString());
                card.setFlavorText(Objects.nonNull(entity.get("flavor_text")) ? entity.get("flavor_text").toString() : null);
                card.setArtist(entity.get("artist").toString());
                card.setBorderColor(entity.get("border_color").toString());

                card.setFrame(entity.get("frame").toString());
                card.setFullArt((Boolean) entity.get("full_art"));
                card.setTextless((Boolean) entity.get("textless"));
                card.setBooster((Boolean) entity.get("booster"));
                card.setStorySpotlight((Boolean) entity.get("story_spotlight"));
                card.setEdhrecRank((Integer) entity.get("edhrec_rank"));
                Map<String, String> prices = (Map<String, String>) entity.get("prices");
                BigDecimal price = null;
                BigDecimal priceFoil = null;
                for (Map.Entry<String, String> entry : prices.entrySet()) {
                    if ("eur".equals(entry.getKey())) {
                        price = Objects.nonNull(entry.getValue()) ? new BigDecimal(entry.getValue()) : null;
                    }
                    if ("eur_foil".equals(entry.getKey())) {
                        priceFoil = Objects.nonNull(entry.getValue()) ? new BigDecimal(entry.getValue()) : null;
                    }
                }
                card.setPrice(price);
                card.setPriceFoil(priceFoil);

                if (Objects.nonNull(entity.get("card_faces"))) {
                    List<Object> card_faces = (List<Object>) entity.get("card_faces");

                    int index = 0;
                    for (Object cardFace : card_faces) {
                        if (cardFace instanceof Map) {
                            Map<String, Object> cardFaceMap = (Map<String, Object>) cardFace;
                            if (index == 0) {
                                card.setNameFace1(Objects.nonNull(cardFaceMap.get("name")) ? cardFaceMap.get("name").toString() : null);
                                card.setPrintedNameFace1(Objects.nonNull(cardFaceMap.get("printed_name")) ? cardFaceMap.get("printed_name").toString() : null);
                                card.setPrintedTextFace1(Objects.nonNull(cardFaceMap.get("printed_text")) ? cardFaceMap.get("printed_text").toString() : null);
                                card.setTypeLineFace1(Objects.nonNull(cardFaceMap.get("type_line")) ? cardFaceMap.get("type_line").toString() : null);
                                card.setPrintedTypeLineFace1(Objects.nonNull(cardFaceMap.get("printed_type_line")) ? cardFaceMap.get("printed_type_line").toString() : null);
                                card.setManaCostFace1(Objects.nonNull(cardFaceMap.get("printed_type_line")) ? cardFaceMap.get("printed_type_line").toString() : null);
                                card.setFlavorTextFace1(Objects.nonNull(cardFaceMap.get("flavor_text")) ? cardFaceMap.get("flavor_text").toString() : null);
                                card.setPowerFace1(Objects.nonNull(cardFaceMap.get("power")) ? cardFaceMap.get("power").toString() : null);
                                card.setToughnessFace1(Objects.nonNull(cardFaceMap.get("toughness")) ? cardFaceMap.get("toughness").toString() : null);
                                if (Objects.nonNull(cardFaceMap.get("image_uris"))) {
                                    Map<String, String> image = (Map<String, String>) cardFaceMap.get("image_uris");
                                    String pngImageFace = "";
                                    for (Map.Entry<String, String> entry : image.entrySet()) {
                                        if ("png".equals(entry.getKey())) {
                                            pngImageFace = entry.getValue();
                                        }
                                    }
                                    card.setImageFace1(pngImageFace);
                                }
                            }
                            if (index == 1) {
                                card.setNameFace2(Objects.nonNull(cardFaceMap.get("name")) ? cardFaceMap.get("name").toString() : null);
                                card.setPrintedNameFace2(Objects.nonNull(cardFaceMap.get("printed_name")) ? cardFaceMap.get("printed_name").toString() : null);
                                card.setPrintedTextFace2(Objects.nonNull(cardFaceMap.get("printed_text")) ? cardFaceMap.get("printed_text").toString() : null);
                                card.setTypeLineFace2(Objects.nonNull(cardFaceMap.get("type_line")) ? cardFaceMap.get("type_line").toString() : null);
                                card.setPrintedTypeLineFace2(Objects.nonNull(cardFaceMap.get("printed_type_line")) ? cardFaceMap.get("printed_type_line").toString() : null);
                                card.setManaCostFace2(Objects.nonNull(cardFaceMap.get("printed_type_line")) ? cardFaceMap.get("printed_type_line").toString() : null);
                                card.setFlavorTextFace2(Objects.nonNull(cardFaceMap.get("flavor_text")) ? cardFaceMap.get("flavor_text").toString() : null);
                                card.setPowerFace2(Objects.nonNull(cardFaceMap.get("power")) ? cardFaceMap.get("power").toString() : null);
                                card.setToughnessFace2(Objects.nonNull(cardFaceMap.get("toughness")) ? cardFaceMap.get("toughness").toString() : null);
                                if (Objects.nonNull(cardFaceMap.get("image_uris"))) {
                                    Map<String, String> image = (Map<String, String>) cardFaceMap.get("image_uris");
                                    String pngImageFace = "";
                                    for (Map.Entry<String, String> entry : image.entrySet()) {
                                        if ("png".equals(entry.getKey())) {
                                            pngImageFace = entry.getValue();
                                        }
                                    }
                                    card.setImageFace2(pngImageFace);
                                }
                            }
                        }
                        index++;
                    }


                }
                cardToSave.add(card);
                if (indexCard == 500) {
                    cardRepository.saveAll(cardToSave);
                    cardToSave.clear();
                    indexCard = 0;
                }
                indexCard++;
            }
            if (!cardToSave.isEmpty()) {
                cardRepository.saveAll(cardToSave);
            }
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
