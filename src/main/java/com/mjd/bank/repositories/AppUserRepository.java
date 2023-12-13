package com.mjd.bank.repositories;

import com.mjd.bank.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByEmail(String email);
}
