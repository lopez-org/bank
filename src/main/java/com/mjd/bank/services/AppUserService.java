package com.mjd.bank.services;

import com.mjd.bank.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService {
  AppUser getUserById(Long id);
  UserDetailsService userDetailsService();
}
