package com.hoadeol.seolo.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Account {

  @Id
  @GeneratedValue
  @OneToOne(mappedBy = "accountId")
  private User id;
}
