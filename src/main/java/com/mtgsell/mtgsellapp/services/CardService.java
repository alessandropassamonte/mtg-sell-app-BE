package com.mtgsell.mtgsellapp.services;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public Page<Card> findPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String basicLand = "%basic land%";
        String terraBase = "%terra base%";
        String token = "%token%";
        return cardRepository.findAllCards(basicLand.toLowerCase(), terraBase.toLowerCase(), token.toLowerCase(), pageRequest);
    }

    public Page<Card> findByNamePaginated(int page, int size, String search) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String basicLand = "%basic land%";
        String terraBase = "%terra base%";
        String token = "%token%";
        return cardRepository.findAllCardsByName(basicLand.toLowerCase(), terraBase.toLowerCase(), token.toLowerCase(), search, pageRequest);
    }
}
