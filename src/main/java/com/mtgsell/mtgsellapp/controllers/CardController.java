package com.mtgsell.mtgsellapp.controllers;

import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                    @RequestParam(defaultValue = "12") int size) {
        Page<Card> resultPage = cardService.findPaginated(page, size);
        return resultPage;
    }


    @GetMapping("/search")
    public Page<Card> findPaginated(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "12") int size, @RequestParam String search) {
        Page<Card> resultPage = cardService.findByNamePaginated(page, size, search);
        return resultPage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById( @PathVariable String id) {
        return ResponseEntity.ok(cardService.findById(id));
    }
}
