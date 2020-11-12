package com.example.qars.controller;

import com.example.qars.entity.User;
import com.example.qars.security.AuthenticationRequest;
import com.example.qars.security.AuthenticationResponse;
import com.example.qars.security.JwtUtil;
import com.example.qars.service.UserService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    /** Services & Components **/
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    LoginController(AuthenticationManager authenticationManager,
                    UserService userService,
                    JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody AuthenticationRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // If this fails, throw a exception
        } catch (BadCredentialsException e){ throw new Exception("username of wachtwoord incorrect", e); }

        // Survived try catch block, retrieve userDetails by username
        final User user = userService.loadUserByUsername(request.getUsername());

        // Use userDetails to generate token
        final String jwt = jwtUtil.generateToken(user);

        // Return token to user
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}