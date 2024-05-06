package com.mtgsell.mtgsellapp.services;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.UserCard;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import com.mtgsell.mtgsellapp.repositories.UserCardRepository;
import com.mtgsell.mtgsellapp.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class UserCardService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserCardRepository userCardRepository;

    @Autowired
    private JwtService jwtService;

    public Page<UserCard> getCardsByUser(HttpServletRequest request, Pageable pageable) {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        if (userEntity != null) {

            return userCardRepository.findAllByUser(userEntity, pageable);
        }
        return Page.empty();
    }


    @Transactional
    public void addCardsToCurrentUser(UserCard userCard, HttpServletRequest request) {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        userCard.setUsers(userEntity);
        userCard.setCard(cardRepository.findById(userCard.getCard().getId()).orElseThrow());
        userCard.setDate(new Date());
        userCardRepository.save(userCard);
    }

    @Transactional
    public UserCard update(UserCard userCard, HttpServletRequest request) {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        Card card = cardRepository.findById(userCard.getCard().getId()).orElseThrow();
        userCard.setCard(card);
        userCard.setUsers(userEntity);
        return userCardRepository.save(userCard);
    }

    public List<Card> findAutocomplete(String search, HttpServletRequest request) {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        String basicLand = "basic %";
        String token = "token%";
        return userCardRepository.findAutocomplete(basicLand.toLowerCase(),  token.toLowerCase(), search, user).orElseThrow();
    }

    @Transactional
    public void addAllToCurrentUser(List<UserCard> userCards, HttpServletRequest request) {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();

        for(UserCard userCardItem: userCards){
            UserCard newUserCard = new UserCard();
            newUserCard.setUsers(userEntity);
            newUserCard.setDate(new Date());
            newUserCard.setCard(userCardItem.getCard());
            newUserCard.setFoil(userCardItem.getFoil());
            newUserCard.setLang(userCardItem.getLang());
            newUserCard.setAttivo(userCardItem.getAttivo());
            newUserCard.setInVendita(userCardItem.getInVendita());
            userCardRepository.save(newUserCard);
        }
    }
}
