package com.emp.service;

import com.emp.dto.RegisterRequest;
import com.emp.repository.UserRepository;
import com.emp.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void testRegister() {

        // Create sample request
        RegisterRequest request = new RegisterRequest();
        request.setUsername("admin");
        request.setEmail("admin@example.com");
        request.setPassword("admin123");

        // Mock repository behavior
        when(userRepository.existsByUsername("admin"))
                .thenReturn(false);

        when(userRepository.existsByEmail("admin@example.com"))
                .thenReturn(false);

        // Mock password encoding
        when(passwordEncoder.encode("admin123"))
                .thenReturn("encodedPassword");

        // Call the method
        String result = authService.register(request);

        // Verify the result
        assertEquals("User registered successfully", result);

        // Verify save() was called once
        verify(userRepository, times(1))
                .save(any());
    }
}