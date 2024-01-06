package com.hoadeol.seolo.exception.member;

public class DuplicateMemberIdException extends RuntimeException {

  public DuplicateMemberIdException(String memberId) {
    super("Duplicate memberId: " + memberId);
  }
}