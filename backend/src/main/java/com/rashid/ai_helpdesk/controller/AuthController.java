package com.rashid.ai_helpdesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rashid.ai_helpdesk.payload.request.LoginRequest;
import com.rashid.ai_helpdesk.payload.request.SignUpRequest;
import com.rashid.ai_helpdesk.payload.response.JwtResponse;
import com.rashid.ai_helpdesk.payload.response.MessageResponse;
import com.rashid.ai_helpdesk.security.jwt.JwtUtils;
import com.rashid.ai_helpdesk.security.jwt.UserDetailsImpl;
import com.rashid.ai_helpdesk.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils,
            UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userDetailsService.assureEmailExists(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use."));
        }

        userDetailsService.createUser(
                signUpRequest.getEmail(),
                signUpRequest.getUsername(),
                signUpRequest.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("User registered successfully."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserEmail(),
                            loginRequest.getPassword()));

            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return ResponseEntity.ok(
                    new JwtResponse(
                            jwt,
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            List.of("ROLE_USER")));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid username/email or password."));
        }
    }

    @DeleteMapping("/deletemyAccount")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Unauthorized."));
        }

        boolean deleted = userDetailsService.deleteById(user.getId());

        if (deleted) {
            return ResponseEntity.ok(new MessageResponse("Your account was deleted successfully."));


            /*

            später kannst du noch dazu zu loggout redirecten
             */
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("Account could not be deleted."));
    }
}
