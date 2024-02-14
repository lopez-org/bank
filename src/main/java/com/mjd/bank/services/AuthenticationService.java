package com.mjd.bank.services;

import com.mjd.bank.dtos.request.LoginRequest;
import com.mjd.bank.dtos.request.SignupRequest;
import com.mjd.bank.dtos.response.JwtAuthenticationResponse;

public interface AuthenticationService {
  JwtAuthenticationResponse signup(SignupRequest request);
  JwtAuthenticationResponse login(LoginRequest request);

}
