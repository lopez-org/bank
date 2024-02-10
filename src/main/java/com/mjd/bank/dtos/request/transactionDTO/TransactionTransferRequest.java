package com.mjd.bank.dtos.request.transactionDTO;

import java.math.BigDecimal;

public record TransactionTransferRequest(Long from, Long to, BigDecimal amount, String description) { }
