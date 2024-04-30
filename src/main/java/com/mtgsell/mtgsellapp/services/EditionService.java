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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EditionService {

    @Autowired
    EditionRepository editionRepository;

    @Transactional
    public void updateAllCardMarketNames() {
        List<Edition> editions = editionRepository.findAll();
        List<Edition> updated = new ArrayList<>();

        for (Edition edition : editions) {
            Edition updateEdition = edition;
            updateEdition.setCardMarketName(updateNameCardMarket(edition.getSetName()));
            updated.add(updateEdition);
        }

        editionRepository.saveAll(updated);
    }

    public String updateNameCardMarket(String setName) {
        String result = setName;
        if (result.equals("Revised Edition")) {
            result = "Revised";
        }

        // Controlla se la parola "Commander" è presente e la mette in prima posizione
        Pattern pattern = Pattern.compile("(.*?)(\\bCommander\\b)(.*)");
        Matcher matcher = pattern.matcher(result);
        if (matcher.matches()) {
            result = "Commander " + matcher.group(1) + matcher.group(3);
        }
        result = result.replaceAll("[^\\w\\s]", "").replaceAll("\\s+", "-");
        result = result.replaceAll("^-|-$", "");
        return result;
    }

}
