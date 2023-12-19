package com.mjd.bank.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mjd.bank.entities.AccountType;
import java.math.BigDecimal;

/**
 * DTO containing the details of an account
 * @param accountNumber The account number
 * @param accountType The type of account - see {@link AccountType}
 * @param ownerName The name of the owner of the account
 * @param ownerEmail The email of the owner of the account
 * @param balance The amount of money in the account
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountDetailDTO(Long accountNumber, AccountType accountType, String ownerName, String ownerEmail, BigDecimal balance) { }
