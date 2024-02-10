package com.mjd.bank.services;

import com.mjd.bank.dtos.request.accountDTO.AccountCreationRequest;
import com.mjd.bank.dtos.request.accountDTO.AccountDepositRequest;
import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;


public interface AccountService {
  SimpleMessageResponse create(Long ownerId, AccountCreationRequest creationRequest);

  /**
   * Deposit money to an account
   * @param ownerId The id of the owner of the account
   * @param depositRequest The details of the deposit - see {@link AccountDepositRequest}
   * @return A {@link SimpleMessageResponse} indicating the result of the operation.
   * If the operation was successful, the message will be "Deposit of {amount} to account {accountId} completed"
   */
  SimpleMessageResponse deposit(Long ownerId, AccountDepositRequest depositRequest);

  /**
   * Get the details of an account
   * @param ownerId The id of the owner of the account
   * @param accountId The id of the account
   * @return An {@link AccountDetailDTO} containing the details of the account
   */
  AccountDetailDTO getAccountDetail(Long ownerId, Long accountId);
  Account getAccountById(Long id);
  void save(Account account);
}
