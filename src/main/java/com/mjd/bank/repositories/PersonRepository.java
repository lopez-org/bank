package com.mjd.bank.repositories;

import com.mjd.bank.entities.AppUser;
import com.mjd.bank.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  Person findAllByCity(String city);
  Person findAllByAddress(String address);
  Person findAllByLastName(String lastName);
}
