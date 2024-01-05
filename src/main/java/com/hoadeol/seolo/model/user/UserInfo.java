package com.hoadeol.seolo.model.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDateTime;
import lombok.Value;

@Embeddable
@Value
public class UserInfo {

  String userId;  //유저가 회원가입 시 입력한 id
  String name;
  String tel; //TODO 전화번호 유효성
  String nickName;
  String email; //TODO 이메일 유효성
  String profilePicturePath;
  @Embedded
  Address address;
  LocalDateTime registrationDate;

  public UserInfo() {
    userId = null;
    name = null;
    tel = null;
    nickName = null;
    email = null;
    profilePicturePath = null;
    address = null;
    registrationDate = null;
  }

  public UserInfo(String userId, String name, String tel, String nickName, String email,
      String profilePicturePath, Address address, LocalDateTime registrationDate) {
    this.userId = userId;
    this.name = name;
    this.tel = tel;
    this.nickName = nickName;
    this.email = email;
    this.profilePicturePath = profilePicturePath;
    this.address = address;
    this.registrationDate = registrationDate;
  }
}
