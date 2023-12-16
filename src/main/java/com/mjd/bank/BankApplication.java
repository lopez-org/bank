package com.mjd.bank;

import com.mjd.bank.entities.Account;
import com.mjd.bank.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AccountRepository accountRepository) {
		return args -> {
			for (int i = 0; i < 10; i++) {
				Account account = new Account();
				accountRepository.save(account);
			}
		};
	}
}
