package com.mtgsell.mtgsellapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgsell.mtgsellapp.dto.request.CardAddUserRequest;
import com.mtgsell.mtgsellapp.entities.*;
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
import java.io.FileNotFoundException;
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
    @GetMapping("/userInSession")
    public ResponseEntity<?> userInSession(HttpServletRequest request) {
        return ResponseEntity.ok(userService.userInSession(request));
    }

    @GetMapping("/salva")
    public ResponseEntity<?> salva(HttpServletRequest request) throws IOException {
    this.userService.salva(request);
        return ResponseEntity.ok("OK");
    }





}
