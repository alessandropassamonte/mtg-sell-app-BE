package com.mtgsell.mtgsellapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgsell.mtgsellapp.entities.Card;
import com.mtgsell.mtgsellapp.entities.Edition;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.services.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
    EditionService editionService;

    @GetMapping(value="/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() throws IOException, ParseException {
        return ResponseEntity.ok("OK");

    }
}