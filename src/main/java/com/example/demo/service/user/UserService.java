package com.example.demo.service.user;

import com.example.demo.dto.user.UserRegistrationRequestDto;
import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.exception.RegistrationException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    User getUser();
}
