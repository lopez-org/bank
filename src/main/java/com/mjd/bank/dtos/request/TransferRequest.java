package com.mjd.bank.dtos.request;

import java.math.BigDecimal;

public record TransferRequest(Long from, Long to, BigDecimal amount, String description) { }
