package com.mjd.bank.controllers;

import com.mjd.bank.dtos.request.LoginRequest;
import com.mjd.bank.dtos.request.SignupRequest;
import com.mjd.bank.dtos.response.JwtAuthenticationResponse;
import com.mjd.bank.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public JwtAuthenticationResponse login(@RequestBody LoginRequest request) {
    return authenticationService.login(request);
  }

  @PostMapping("/signup")
  public JwtAuthenticationResponse signup(@RequestBody SignupRequest request) {
    return authenticationService.signup(request);
  }
}
