package com.hoadeol.seolo.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WithdrawnType {
  //TODO 탈퇴 사유
  NUM1("사유"),
  NUM2("사유");

  private final String reason;
}
