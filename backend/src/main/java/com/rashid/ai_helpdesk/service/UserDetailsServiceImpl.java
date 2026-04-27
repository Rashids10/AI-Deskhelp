package com.rashid.ai_helpdesk.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rashid.ai_helpdesk.entity.UserEntity;
import com.rashid.ai_helpdesk.repository.UserRepository;

/*

Service, der von Spring Security verwendet wird,
 um User beim Login aus der Datenbank zu laden
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username or email: " + userEmail));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList());
    }
}
