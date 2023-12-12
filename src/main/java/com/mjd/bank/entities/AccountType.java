package com.mjd.bank.entities;

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
  public String toString() {
    return this.name().toLowerCase(Locale.ROOT);
  }
}
