package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.dao.entitys.User;
import com.aladdin.universitymanagement.dao.repositorys.UserRepository;
import com.aladdin.universitymanagement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public boolean activateUser(String token) {
        if (!jwtUtil.validateToken(token)) {
            return false;
        }

        String email = jwtUtil.extractEmail(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
