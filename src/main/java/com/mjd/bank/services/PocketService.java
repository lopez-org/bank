package com.mjd.bank.services;

import com.mjd.bank.dtos.request.PocketTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;

public interface PocketService {

  /**
   * Deposit money from an account to a pocket
   * @param ownerId The id of the owner of the account
   * @param pocketTransferRequest The request containing the account and pocket numbers and the amount to transfer
   * @return A {@link SimpleMessageResponse} containing a message with the result of the operation
   */
  SimpleMessageResponse depositFromAccount(Long ownerId, PocketTransferRequest pocketTransferRequest);
}
