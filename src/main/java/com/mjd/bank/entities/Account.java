package com.mjd.bank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity(name = "account")
public class Account {

  @Id
  private Long number;

  @Enumerated(value = EnumType.STRING)
  private AccountType type;
  private BigDecimal balance;

  @OneToMany(mappedBy = "account", orphanRemoval = true)
  private List<Pocket> pockets = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private AppUser owner;

  @PrePersist
  private void generateRandomNumbers() {
    this.number = generateRandomAccountNumber();
  }

  private Long generateRandomAccountNumber() {
    long smallest = 1000_0000_0000_0000L;
    long biggest =  9999_9999_9999_9999L;

    return ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
  }
}
