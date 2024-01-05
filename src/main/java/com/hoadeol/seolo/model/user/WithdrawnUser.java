package com.hoadeol.seolo.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class WithdrawnUser {

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne
  @JoinColumn(name = "ACCOUNT_ID")
  private Account account;
  private UserInfo userInfo;
  private WithdrawnType withdrawnType;
  private String comment;
  private LocalDateTime withdrawnDate;

}
