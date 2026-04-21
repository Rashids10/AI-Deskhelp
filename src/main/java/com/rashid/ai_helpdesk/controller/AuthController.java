package com.rashid.ai_helpdesk.controller;

import org.apache.catalina.connector.Response;

import  com.rashid.ai_helpdesk.payload.request.SignUpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rashid.ai_helpdesk.repository.UserRepository;
import com.rashid.ai_helpdesk.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;


@RestController

@RequestMapping("/api/auth")

@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {




    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/signUp")


    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){


        if(userRepository.existsByEmail(signupReqeust.getEmail())){
            return ResponseEntity.
            badRequest().
            body(new MessageResponse("OOOOPs, this Email is already in use, please choose another email or log in with this email!!"));


        }
                    //Create new user's account

        User user = new User(signupReqeust.getEmail(userEmail),
        encoder.encode(signupReqeust.getPassword()));
    
    }


        @PostMapping("/signIn")


        public ResponseEntity<?> athenticateUser(@Valid SignUpRequest signUpRequest){
            if (userRepository.existsByEmail(signUpRequest.getEmail(userEmail).))
        }

}

    


















/*




@CrossOrigin erlaubt externe Requests
origins="*" = alles erlaubt (unsicher)
maxAge=3600 = Cache für 1 Stunde
wichtig für Frontend ↔ Backend Kommunikation


*/ 

    
