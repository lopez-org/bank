package com.mjd.bank.dtos.request.pocketDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PocketTransferRequest(Long accountNumber, Long pocketNumber, BigDecimal amount) { }
