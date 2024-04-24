package com.mtgsell.mtgsellapp.controllers;
import com.mtgsell.mtgsellapp.dto.request.LoginRequest;
import com.mtgsell.mtgsellapp.dto.request.RegisterUserRequest;
import com.mtgsell.mtgsellapp.dto.response.JwtResponse;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.services.AuthenticationService;
import com.mtgsell.mtgsellapp.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserRequest registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }


}