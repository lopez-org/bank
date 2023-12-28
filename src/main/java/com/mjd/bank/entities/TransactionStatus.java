package com.mjd.bank.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Locale;

public enum TransactionStatus {
  PENDING,
  COMPLETED,
  FAILED,
  UNKNOWN;

  public static TransactionStatus getTransactionStatus(String transactionStatus) {
    try {
      return TransactionStatus.valueOf(transactionStatus);
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
