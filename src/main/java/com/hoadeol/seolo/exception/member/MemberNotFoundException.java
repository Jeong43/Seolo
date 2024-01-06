package com.hoadeol.seolo.exception.member;

public class MemberNotFoundException extends RuntimeException {

  public MemberNotFoundException(Long id) {
    super("Member not found with memberId: " + id);
  }
}