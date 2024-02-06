package com.mjd.bank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
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
@Entity(name = "user")
public class AppUser extends Person {
  private String phoneNumber;
  private String email;
  private String password;

  @OneToMany(mappedBy = "owner", orphanRemoval = true)
  private List<Account> accounts = new ArrayList<>();

}
