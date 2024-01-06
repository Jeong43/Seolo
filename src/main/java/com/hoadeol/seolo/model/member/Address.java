package com.hoadeol.seolo.model.member;

import jakarta.persistence.Embeddable;
import lombok.Value;

@Embeddable
@Value
public class Address {

  //TODO NotNull 설정 넣기
  String roadAddress;
  String detailAddress;

  public Address() {
    roadAddress = null;
    detailAddress = null;
  }

  public Address(String roadAddress, String detailAddress) {
    this.roadAddress = roadAddress;
    this.detailAddress = detailAddress;
  }
}
