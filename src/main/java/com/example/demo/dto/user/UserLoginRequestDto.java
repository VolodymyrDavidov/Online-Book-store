package com.example.demo.dto.user;

import com.example.demo.service.PasswordValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public record UserLoginRequestDto(
        @NotEmpty
        @Size(min = 8, max = 20)
        @Email
        String email,
        @PasswordValidator
        String password
) {
}
