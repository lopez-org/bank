package com.mjd.bank.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mjd.bank.entities.AccountType;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO containing the details of an account
 * @param accountNumber The account number
 * @param accountType The type of account - see {@link AccountType}
 * @param ownerName The name of the owner of the account
 * @param ownerEmail The email of the owner of the account
 * @param balance The amount of money in the account
 * @param pockets A list containing the pockets of the account - see {@link PocketDetailDTO}
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountDetailDTO(Long accountNumber, AccountType accountType, String ownerName, String ownerEmail, BigDecimal balance, List<PocketDetailDTO> pockets) { }
