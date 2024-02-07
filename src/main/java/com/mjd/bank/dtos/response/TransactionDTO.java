package com.mjd.bank.dtos.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class TransactionDTO {
  private Long id;
  private Long accountNumber;
  private String type;
  private BigDecimal amount;
  private String date;
  private String description;
}
