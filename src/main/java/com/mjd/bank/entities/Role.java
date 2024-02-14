package com.mjd.bank.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Locale;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  SUPER_ADMIN,
  ADMIN,
  USER,
  UNKNOWN;

  public static Role getRole(String role) {
    try {
      return Role.valueOf(role.toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException e) {
      return UNKNOWN;
    }
  }

  @Override
  public String getAuthority() {
    return this.toString();
  }

  @JsonValue
  public String toString() {
    return name().toLowerCase(Locale.ROOT);
  }
}
