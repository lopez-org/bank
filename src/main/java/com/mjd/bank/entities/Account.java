package com.mjd.bank.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Pocket> pockets;

  @OneToMany(mappedBy = "to", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Transaction> transactionsTo;

  @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Transaction> transactionsFrom;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private AppUser owner;

  public Account(Long number, AccountType type, BigDecimal balance, AppUser owner) {
    this.number = number;
    this.type = type;
    this.balance = balance;
    this.owner = owner;
  }

  public void addPocket(Pocket pocket) {
    if (this.pockets == null) {
      this.pockets = new ArrayList<>();
    }
    pocket.setAccount(this);
    this.pockets.add(pocket);
  }

  public void addPockets(List<Pocket> pockets) {
    pockets.forEach(this::addPocket);
  }

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
