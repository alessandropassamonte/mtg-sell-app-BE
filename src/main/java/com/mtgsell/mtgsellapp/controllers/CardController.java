package com.mtgsell.mtgsellapp.controllers;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RequestMapping("/card")
@RestController
public class CardController {

    @Autowired
    CardService cardService;



    @GetMapping("/all")
    public Page<Card> findPaginated(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size) {
        Page<Card> resultPage = cardService.findPaginated(page, size);

        return resultPage;
    }
}
