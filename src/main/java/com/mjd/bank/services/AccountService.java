package com.mjd.bank.services;

import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import java.math.BigDecimal;

public interface AccountService {

  /**
   * Deposit money to an account
   * @param ownerId The id of the owner of the account
   * @param accountId The id of the account
   * @param amount The amount of money to deposit
   * @return A {@link SimpleMessageResponse} indicating the result of the operation.
   * If the operation was successful, the message will be "Deposit of {amount} to account {accountId} completed"
   */
  SimpleMessageResponse deposit(Long ownerId, Long accountId, BigDecimal amount);

  /**
   * Get the details of an account
   * @param ownerId The id of the owner of the account
   * @param accountId The id of the account
   * @return An {@link AccountDetailDTO} containing the details of the account
   */
  AccountDetailDTO getAccountDetail(Long ownerId, Long accountId);
}
