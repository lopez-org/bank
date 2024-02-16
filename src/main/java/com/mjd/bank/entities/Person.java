package com.mjd.bank.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public class Person {
  @Id
  private Long id;
  private String name;
  private String lastName;
  private String city;
  private String address;
}
