package com.mjd.bank.utils.mappers;

import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.entities.Account;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountMapper {

  public AccountDetailDTO toAccountDetailDTO(Account account) {
    String completeName = String.format("%s %s", account.getOwner().getName(), account.getOwner().getLastName());
    return new AccountDetailDTO(account.getNumber(), completeName, account.getOwner().getEmail(), account.getBalance());
  }

}
