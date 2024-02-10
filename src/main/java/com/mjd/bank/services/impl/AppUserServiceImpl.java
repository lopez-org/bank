package com.mjd.bank.services.impl;

import com.mjd.bank.entities.AppUser;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AppUserRepository;
import com.mjd.bank.services.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

  private final AppUserRepository appUserRepository;

  public AppUserServiceImpl(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public AppUser getUserById(Long id) {
    return appUserRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("The user with ID " + id + " doesn't exist"));
  }
}
