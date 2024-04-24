package com.mtgsell.mtgsellapp.controllers;

import com.mtgsell.mtgsellapp.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("OK");
    }
}