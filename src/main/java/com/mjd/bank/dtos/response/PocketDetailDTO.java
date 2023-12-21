package com.mjd.bank.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;

/**
 * DTO containing the details of a pocket
 * @param pocketNumber The pocket number
 * @param pocketName The name of the pocket
 * @param balance The amount of money in the pocket
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PocketDetailDTO(Long pocketNumber, String pocketName, BigDecimal balance) { }
