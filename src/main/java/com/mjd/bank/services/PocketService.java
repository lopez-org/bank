package com.mjd.bank.services;

import com.mjd.bank.dtos.request.pocketDTO.PocketCreationRequest;
import com.mjd.bank.dtos.request.pocketDTO.PocketTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Pocket;

public interface PocketService {

  /**
   * Deposit money from an account to a pocket
   * @param ownerId The id of the owner of the account
   * @param pocketTransferRequest The request containing the account and pocket numbers and the amount to transfer
   * @return A {@link SimpleMessageResponse} containing a message with the result of the operation
   */
  SimpleMessageResponse depositFromAccount(Long ownerId, PocketTransferRequest pocketTransferRequest);
  SimpleMessageResponse create(Long ownerId, PocketCreationRequest creationRequest);
  Pocket getPocketById(Long id);
  void save (Pocket pocket);
}
