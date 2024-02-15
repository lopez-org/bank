package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.request.LoginRequest;
import com.mjd.bank.dtos.request.SignupRequest;
import com.mjd.bank.dtos.response.JwtAuthenticationResponse;
import com.mjd.bank.entities.AppUser;
import com.mjd.bank.entities.Role;
import com.mjd.bank.repositories.AppUserRepository;
import com.mjd.bank.services.AuthenticationService;
import com.mjd.bank.services.JwtService;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AppUserRepository appUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
    this.appUserRepository = appUserRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public JwtAuthenticationResponse signup(SignupRequest request) {
    AppUser appUser = new AppUser();
    appUser.setEmail(request.getEmail())
        .setPassword(passwordEncoder.encode(request.getPassword()))
        .setRoles(Set.of(Role.USER))
        .setName(request.getName())
        .setLastName(request.getLastName())
        .setId(request.getDni());

    appUserRepository.save(appUser);

    String jwt = jwtService.generateToken(appUser);
    return JwtAuthenticationResponse.builder()
        .token(jwt)
        .build();
  }

  @Override
  public JwtAuthenticationResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    AppUser appUser = appUserRepository.findByEmail(
        request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("The user with email " + request.getEmail() + " doesn't exist")
    );

    String jwt = jwtService.generateToken(appUser);

    return JwtAuthenticationResponse.builder()
        .token(jwt)
        .build();
  }
}
