package com.mjd.bank.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Locale;

public enum TransactionType {
  DEPOSIT,
  WITHDRAW,
  TRANSFER,
  UNKNOWN;

  public static TransactionType getTransactionType(String transactionType) {
    try {
      return TransactionType.valueOf(transactionType.toUpperCase(Locale.ROOT));
    } catch (Exception e) {
      return UNKNOWN;
    }
  }

  @Override
  @JsonValue
  public String toString() {
    return this.name().toLowerCase(Locale.ROOT);
  }
}
