package com.mtgsell.mtgsellapp.controllers;

import com.mtgsell.mtgsellapp.dto.response.PriceResponse;
import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.services.CardService;
import com.mtgsell.mtgsellapp.services.EditionService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    @Autowired
    EditionService editionService;

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
    public ResponseEntity<Card> findById(@PathVariable String id, @RequestParam(required = false, defaultValue = "false") Boolean isFoil) throws IOException {
        return ResponseEntity.ok(cardService.findById(id, isFoil));
    }

    @GetMapping("/searchAutocomplete")
    public List<Card> findAutocomplete(@RequestParam String search) {
        List<Card> result = cardService.findAutocomplete(search);
        return result;
    }


    @GetMapping("/scraping")
    public ResponseEntity<?> scraping(@RequestParam String setName, @RequestParam String cardName, @RequestParam(required = false, defaultValue = "false") Boolean isFoil) throws IOException {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(cardService.scraping(setName, cardName, isFoil));
        return ResponseEntity.ok(priceResponse);
    }


    @GetMapping("/updateEdition")
    public ResponseEntity<?> scraping() {
        editionService.updateAllCardMarketNames();
        return ResponseEntity.ok("OK");
    }

}
