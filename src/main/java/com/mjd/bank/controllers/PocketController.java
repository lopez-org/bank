package com.mjd.bank.controllers;

import com.mjd.bank.dtos.request.PocketTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.services.PocketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pockets")
public class PocketController {

  private final PocketService pocketService;

  public PocketController(PocketService pocketService) {
    this.pocketService = pocketService;
  }

  @PostMapping("/transfer")
  public SimpleMessageResponse transfer(@RequestHeader(name = "owner_id")Long ownerId, @RequestBody PocketTransferRequest transferRequest) {
    return pocketService.depositFromAccount(ownerId, transferRequest);
  }
}
