package com.mjd.bank.services;

import javax.security.auth.login.CredentialExpiredException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
  String extractUsername(String token);
  String generateToken(UserDetails userDetails);
  boolean isTokenValid(String token, UserDetails userDetails);

}
