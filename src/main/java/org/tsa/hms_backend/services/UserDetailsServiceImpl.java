package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.config.JWTService;
import org.tsa.hms_backend.dtos.LoginDto;
import org.tsa.hms_backend.exceptions.BadLoginException;
import org.tsa.hms_backend.exceptions.UserNotFoundException;
import org.tsa.hms_backend.repositories.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public String signIn(LoginDto loginDTO) {
        String username = loginDTO.getEmail();
        log.debug("Try to login with username: {}", username);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    loginDTO.getPassword()
            ));
        } catch (AuthenticationException ignored) {
            throw new BadLoginException();
        }

        var user = this.loadUserByUsername(username);

        return jwtService.generateToken(user);
    }
}