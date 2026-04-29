package com.rashid.ai_helpdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rashid.ai_helpdesk.entity.UserEntity;
import com.rashid.ai_helpdesk.repository.UserRepository;
import com.rashid.ai_helpdesk.security.jwt.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + userEmail));

        return new UserDetailsImpl(user);
    }

    @Transactional
    public void createUser(String email, String username, String password) {

        if (userRepository.findByEmail(email).isPresent()) {

            throw new RuntimeException("User already exists");

        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        System.out.println("Successfully Created this User" + username);
    }

    public UserEntity findUserById(Long userId) {

        try {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        } catch (Exception e) {
            System.out.println("OOOPs there's Error getting the user " + e.getMessage());
            return null;
        }
    }


    public List<UserEntity> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users;
    }

    @Transactional
    public void deleteUserByEmail(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
        System.out.println("Succesfully deleted this user" + user.getUsername());
    }

    @Transactional

    public void updateEmail(String email, String newEmail) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found with email"));

        user.setEmail(newEmail);
        System.out.println("Succesfully updated your email");

    }

    @Transactional
    public void updateUsername(String email, String newUsername) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found with email"));

        user.setUsername(newUsername);

        System.out.println("Successfully updated your Username");
    }

    @Transactional

    public void updatePassword(String email, String newPassword) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found with email"));

        user.setPassword(newPassword);

        System.out.println("Successfully updated your password");
    }



    public boolean assureEmailExists(String email) {

    if (email == null || email.isBlank()) {
        throw new IllegalArgumentException("Email must not be empty");
    }

    return userRepository.existsByEmail(email);
}

}
