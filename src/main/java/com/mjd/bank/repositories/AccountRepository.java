package com.mjd.bank.repositories;

import com.mjd.bank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByOwner_Id(Long ownerId);
  Account findByOwner_Email(String email);
}
