package com.mjd.bank.utils.mappers;

import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.dtos.response.PocketDetailDTO;
import com.mjd.bank.entities.Account;
import java.util.List;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountMapper {

  public AccountDetailDTO toAccountDetailDTO(Account account) {
    String completeName = String.format("%s %s", account.getOwner().getName(), account.getOwner().getLastName());

    List<PocketDetailDTO> pockets = account.getPockets().stream()
        .map(pocket -> new PocketDetailDTO(pocket.getId(), pocket.getName(), pocket.getBalance()))
        .toList();

    return new AccountDetailDTO(account.getNumber(), account.getType(), completeName, account.getOwner().getEmail(), account.getBalance(), pockets);
  }

}
