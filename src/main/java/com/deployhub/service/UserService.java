package com.deployhub.service;

import com.deployhub.exception.UserAlreadyExistsException;
import com.deployhub.model.User;
import com.deployhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.deployhub.service.JwtService;
import com.deployhub.dto.LoginResponse;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository ,JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtService = jwtService;
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("VIEWER");
        return userRepository.save(user);
    }

    public LoginResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)     //it returns whole user details if its true
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }
}
