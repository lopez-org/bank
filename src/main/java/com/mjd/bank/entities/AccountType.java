package com.mjd.bank.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Locale;

public enum AccountType {
  SAVINGS,
  CURRENT,
  FIXED_DEPOSIT,
  RECURRING_DEPOSIT,
  UNKNOWN;

  public static AccountType getAccountType(String accountType) {
    try {
      return AccountType.valueOf(accountType);
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
