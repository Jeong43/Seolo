package com.hoadeol.seolo.exception.member;

public class DuplicateNickNameException extends RuntimeException {

  public DuplicateNickNameException(String nickName) {
    super("Duplicate nickName: " + nickName);
  }
}