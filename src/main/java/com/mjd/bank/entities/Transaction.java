package com.mjd.bank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Entity(name = "transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Account from;

  @ManyToOne
  private Account to;

  private BigDecimal amount;

  @Enumerated(value = EnumType.STRING)
  private TransactionType type;

  @Enumerated(value = EnumType.STRING)
  private TransactionStatus status;

  private String description;
  private LocalDateTime createdAt;

  public Transaction(Account from, Account to, BigDecimal amount, TransactionType type, String description) {
    this.from = from;
    this.to = to;
    this.amount = amount;
    this.type = type;
    this.description = description;
  }
}
