package org.example.jwtproject.services;

import org.example.jwtproject.auth.AuthRequest;
import org.example.jwtproject.auth.AuthResponse;
import org.example.jwtproject.auth.RegisterRequest;
import org.example.jwtproject.endpoint.User;
import org.example.jwtproject.endpoint.UserDetailsImpl;
import org.example.jwtproject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        User user = userRepo.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!encoder.matches(request.password(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials");
        return new AuthResponse(jwtService.generateToken(new UserDetailsImpl(user)));
    }

    public void register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.username()))
            throw new IllegalArgumentException("Username already taken");
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(encoder.encode(request.password()));
        userRepo.save(user);
    }
}

