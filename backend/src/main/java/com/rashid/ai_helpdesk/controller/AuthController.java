package com.rashid.ai_helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rashid.ai_helpdesk.entity.UserEntity;
import com.rashid.ai_helpdesk.payload.request.LoginRequest;
import com.rashid.ai_helpdesk.payload.request.SignUpRequest;
import com.rashid.ai_helpdesk.payload.response.JwtResponse;
import com.rashid.ai_helpdesk.payload.response.MessageResponse;
import com.rashid.ai_helpdesk.repository.UserRepository;
import com.rashid.ai_helpdesk.security.jwt.JwtUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    @Operation(summary = "Registriert einen neuen User", description = "Erstellt einen neuen Account mit Email, Username und Passwort")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User erfolgreich registriert"),
            @ApiResponse(responseCode = "400", description = "Ungültige Eingaben")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Username is already taken."));
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Email is already in use."));
        }

        UserEntity user = new UserEntity();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("User registered successfully."));
    }

    @PostMapping("/login")
    @Operation(summary = "User anmelden", description = "Ermöglicht einem User, sich mit Email und Passwort einzuloggen")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User erfolgreich eingeloggt"),
    })

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserEmail(),
                            loginRequest.getPassword()));

            UserEntity user = userRepository.findByEmail(loginRequest.getUserEmail())
                    .orElseThrow(() -> new RuntimeException("User not found."));

            String jwt = jwtUtils.generateJwtToken(authentication);

            return ResponseEntity.ok(
                    new JwtResponse(
                            jwt,
                            user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            List.of("ROLE_USER")));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid username/email or password."));
        }
    }
}
