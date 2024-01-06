package com.hoadeol.seolo.exception.member;

public class DuplicateTelException extends RuntimeException {

  public DuplicateTelException(String tel) {
    super("Duplicate tel: " + tel);
  }
}