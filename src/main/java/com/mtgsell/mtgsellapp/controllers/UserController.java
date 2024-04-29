package com.mtgsell.mtgsellapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgsell.mtgsellapp.dto.request.CardAddUserRequest;
import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.Edition;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.services.EditionService;
import com.mtgsell.mtgsellapp.services.JwtService;
import com.mtgsell.mtgsellapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/cards")
    public Page<Card> getUsername(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "12") int size,
                                  HttpServletRequest request) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userService.getCardsByUser(request, pageRequest);
    }

    @PostMapping("/addCards")
    public ResponseEntity<?> addCardsToUser(@RequestBody CardAddUserRequest cardAddUserRequest, HttpServletRequest request) {
        userService.addCardsToCurrentUser(cardAddUserRequest.getCardsId(), request);
        return ResponseEntity.ok("OK");
    }

}
