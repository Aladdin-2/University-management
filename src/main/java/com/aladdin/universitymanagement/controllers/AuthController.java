package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("com.aladdin/university-site/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestParam("token") String token) {
        boolean activated = authService.activateUser(token);
        if (activated) {
            return ResponseEntity.ok("Hesabınız uğurla aktivləşdirildi.");
        } else {
            return ResponseEntity.badRequest().body("Yanlış və ya istifadə olunmuş token.");
        }
    }
}
