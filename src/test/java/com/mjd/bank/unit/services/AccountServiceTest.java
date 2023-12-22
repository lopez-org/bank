package com.mjd.bank.unit.services;

import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.AccountType;
import com.mjd.bank.entities.AppUser;
import com.mjd.bank.entities.Pocket;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.AppUserRepository;
import com.mjd.bank.services.AccountService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:testcontainers.sql")
public class AccountServiceTest {

  @Container
  @ServiceConnection
  static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.26");

  private final AccountService accountService;
  private final AccountRepository accountRepository;
  private final AppUserRepository userRepository;

  @Autowired
  public AccountServiceTest(AccountRepository accountRepository,
      AccountService accountService,
      AppUserRepository userRepository) {
    this.accountRepository = accountRepository;
    this.accountService = accountService;
    this.userRepository = userRepository;
  }

  @BeforeEach
  void setUp() {
    System.setProperty("TESTCONTAINERS_RYUK_DISABLED", "true");
  }

  @Test
  void shouldReturnAccountDetailDTOWhenGetAccountDetail() {
    Long ownerId = 1L;
    AppUser owner = new AppUser(
        ownerId,
        "John Doe",
        "doe",
        "Taganga",
        "Calle ficticia 1 # 2-3 apto 4",
        "3214567890",
        "sndanskd@famsd.com",
        "miMami"
    );

    List<Pocket> pockets = List.of(
        new Pocket(null, "Pocket 1", new BigDecimal("100.0")),
        new Pocket(null, "Pocket 2", new BigDecimal("200.0")),
        new Pocket(null, "Pocket 3", new BigDecimal("300.0"))
    );

    Account account = new Account(
        null,
        AccountType.SAVINGS,
        new BigDecimal("1000.0"),
        owner
    );

    account.addPockets(pockets);

    userRepository.save(owner);
    Account saved = accountRepository.save(account);

    AccountDetailDTO result = accountService.getAccountDetail(ownerId, account.getNumber());

    Assertions.assertEquals(saved.getNumber(), result.accountNumber());
    Assertions.assertEquals(saved.getType(), result.accountType());
    Assertions.assertEquals(saved.getOwner().getName() + " " + saved.getOwner().getLastName(), result.ownerName());
    Assertions.assertEquals(saved.getOwner().getEmail(), result.ownerEmail());
    Assertions.assertEquals(saved.getBalance().doubleValue(), result.balance().doubleValue());
    Assertions.assertEquals(saved.getPockets().size(), result.pockets().size());
    Assertions.assertEquals(saved.getPockets().get(0).getId(), result.pockets().get(0).pocketNumber());
    Assertions.assertEquals(saved.getPockets().get(0).getName(), result.pockets().get(0).pocketName());
    Assertions.assertEquals(saved.getPockets().get(0).getBalance().doubleValue(), result.pockets().get(0).balance().doubleValue());
    Assertions.assertEquals(saved.getPockets().get(1).getId(), result.pockets().get(1).pocketNumber());
    Assertions.assertEquals(saved.getPockets().get(1).getName(), result.pockets().get(1).pocketName());
    Assertions.assertEquals(saved.getPockets().get(1).getBalance().doubleValue(), result.pockets().get(1).balance().doubleValue());
    Assertions.assertEquals(saved.getPockets().get(2).getId(), result.pockets().get(2).pocketNumber());
    Assertions.assertEquals(saved.getPockets().get(2).getName(), result.pockets().get(2).pocketName());
    Assertions.assertEquals(saved.getPockets().get(2).getBalance().doubleValue(), result.pockets().get(2).balance().doubleValue());

  }

}
