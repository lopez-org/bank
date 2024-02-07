package com.mjd.bank.utils.mappers;

import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.Transaction;
import com.mjd.bank.entities.TransactionStatus;
import com.mjd.bank.entities.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public TransactionDTO toTransactionDTO(Transaction transaction) {
    return new TransactionDTO()
        .setId(transaction.getId())
        .setAccountNumber(transaction.getFrom().getNumber())
        .setType(transaction.getType().toString())
        .setAmount(transaction.getAmount())
        .setDate(transaction.getCreatedAt().toString())
        .setDescription(transaction.getDescription());
  }

  public List<TransactionDTO> toTransactionDTOList(List<Transaction> transactionList) {
    return transactionList.stream().map(this::toTransactionDTO).collect(Collectors.toList());
  }

  public Example<Transaction> buildExampleTransactionQuery(
      Account account,
      String type,
      String status,
      LocalDateTime dateFrom,
      LocalDateTime dateTo,
      BigDecimal amountFrom,
      BigDecimal amountTo
  ) {

    TransactionType transactionType = TransactionType.getTransactionType(type) == TransactionType.UNKNOWN
        ? null : TransactionType.getTransactionType(type);

    TransactionStatus transactionStatus = TransactionStatus.getTransactionStatus(status) == TransactionStatus.UNKNOWN
        ? null : TransactionStatus.getTransactionStatus(status);

    Transaction example = new Transaction()
        .setFrom(account).setType(transactionType)
        .setCreatedAt(dateFrom).setStatus(transactionStatus).setAmount(amountFrom);

    return Example.of(example);
  }
}
