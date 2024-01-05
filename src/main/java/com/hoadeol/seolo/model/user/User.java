package com.hoadeol.seolo.model.user;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue
  private Long id;

  //TODO 유니크 제약, NotNull 제약 설정
  @OneToOne
  @JoinColumn(name = "ACCOUNT_ID")
  private Account account; //계정 고유번호
  private String password;
  @Embedded
  private UserInfo userInfo;
}
