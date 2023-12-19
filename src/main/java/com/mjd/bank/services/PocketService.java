package com.mjd.bank.services;

import com.mjd.bank.dtos.request.PocketTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;

public interface PocketService {
  SimpleMessageResponse depositFromAccount(Long ownerId, PocketTransferRequest pocketTransferRequest);
}
