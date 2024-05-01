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
    public void addCardsToCurrentUser(List<Long> cardIds, HttpServletRequest request) {
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        for (Long cardId : cardIds) {
            Card card = cardRepository.findById(cardId)
                    .orElseThrow(() -> new IllegalArgumentException("Carta non trovata con id: " + cardId));

            UserCard userCard = new UserCard();
            userCard.setUsers(userEntity);
            userCard.setCard(card);

            userCardRepository.save(userCard);

        }
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
}
