package com.aladdin.universitymanagement.email;

import org.springframework.core.io.FileSystemResource;

public interface EmailService {

    default void sendEmail(String to, String subject, String text, FileSystemResource imageFile) {
    }

    default void sendEmail(String to, String subject,String token) {
    }
}
