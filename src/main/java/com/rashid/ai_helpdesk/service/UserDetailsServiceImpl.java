package com.rashid.ai_helpdesk.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.rashid.ai_helpdesk.repository.UserRepository;
import com.rashid.ai_helpdesk.security.jwt.EmailNotFoundException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Autowired


    private EmailNotFoundException emailNotFoundException;


    @Override
    @Transactional
    public UserDetails loadUserByEmail(String UserEmail) throws EmailNotFoundException{
        User user = userRepository.findB)
    }


}
