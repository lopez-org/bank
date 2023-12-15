package com.mjd.bank;

import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.AppUser;
import com.mjd.bank.entities.Person;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.PersonRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
