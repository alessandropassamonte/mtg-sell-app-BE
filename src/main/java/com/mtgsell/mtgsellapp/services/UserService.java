package com.mtgsell.mtgsellapp.services;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.Edition;
import com.mtgsell.mtgsellapp.entities.UserCard;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import com.mtgsell.mtgsellapp.repositories.EditionRepository;
import com.mtgsell.mtgsellapp.repositories.UserCardRepository;
import com.mtgsell.mtgsellapp.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EditionRepository editionRepository;
    @Autowired
    private UserCardRepository userCardRepository;

    public UserEntity saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity userInSession(HttpServletRequest request) {
        String username = jwtService.extractTokenFromRequest(request);
        return userRepository.findByUsername(username).orElseThrow();
    }


    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public void salva(HttpServletRequest request) throws IOException {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        List<UserCard> userCards = new ArrayList<>();
        String filename = "D:\\Download Chrome\\carte nostre pt2.csv";
        try (Reader reader = new FileReader(filename);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {


            for (CSVRecord csvRecord : csvParser) {
                UserCard userCard = new UserCard();
                userCard.setUsers(user);
                userCard.setDate(new Date());
                userCard.setFoil(Objects.nonNull(csvRecord.get("Foil")) && csvRecord.get("Foil").equals("foil"));
                Card card = this.cardRepository.findBySetIt(csvRecord.get("Scryfall ID")).orElseThrow();

                userCard.setCard(card);
                userCard.setAttivo(true);
                userCard.setInVendita(false);
                userCard.setLang(csvRecord.get("Language").equals("it") ? "it" : (csvRecord.get("Language").equals("en") ? "en" : null));
//                userCards.add(userCard);
                this.userCardRepository.save(userCard);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


