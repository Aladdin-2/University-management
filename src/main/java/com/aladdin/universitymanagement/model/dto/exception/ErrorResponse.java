package com.aladdin.universitymanagement.model.dto.exception;

public record ErrorResponse(
        int status,
        String message,
        String details,
        String errorTime
) {
}
