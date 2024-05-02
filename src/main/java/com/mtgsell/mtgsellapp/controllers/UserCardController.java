package com.mtgsell.mtgsellapp.controllers;

import com.mtgsell.mtgsellapp.dto.request.CardAddUserRequest;
import com.mtgsell.mtgsellapp.dto.response.SuccessResponse;
import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.UserCard;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.repositories.CardRepository;
import com.mtgsell.mtgsellapp.repositories.UserCardRepository;
import com.mtgsell.mtgsellapp.repositories.UserRepository;
import com.mtgsell.mtgsellapp.services.JwtService;
import com.mtgsell.mtgsellapp.services.UserCardService;
import com.mtgsell.mtgsellapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/usercard")
@RestController
public class UserCardController {

    @Autowired
    UserCardService userCardService;

    @GetMapping("/cards")
    public Page<UserCard> getUsername(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "12") int size,
                                      HttpServletRequest request) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userCardService.getCardsByUser(request, pageRequest);
    }

    @PostMapping("/addCards")
    public ResponseEntity<?> addCardsToUser(@RequestBody UserCard userCard, HttpServletRequest request) {
        userCardService.addCardsToCurrentUser(userCard, request);
        return ResponseEntity.ok(new SuccessResponse("OK"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserCard userCard, HttpServletRequest request) {
        return ResponseEntity.ok(userCardService.update(userCard, request));
    }
}
