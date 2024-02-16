package com.mjd.bank.dtos.request.accountDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountDepositRequest(Long accountNumber, BigDecimal amount) { }
