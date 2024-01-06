package com.hoadeol.seolo.model.member;

import com.hoadeol.seolo.dto.MemberInfoDto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDateTime;
import lombok.Value;

@Embeddable
@Value
public class MemberInfo {

  String memberId;  //유저가 회원가입 시 입력한 id
  String name;
  String tel; //TODO 전화번호 유효성
  String nickName;
  String email; //TODO 이메일 유효성
  String profilePicturePath;
  @Embedded
  Address address;
  LocalDateTime registrationDate;

  public MemberInfo() {
    this.memberId = null;
    this.name = null;
    this.tel = null;
    this.nickName = null;
    this.email = null;
    this.profilePicturePath = null;
    this.address = null;
    this.registrationDate = null;
  }

  public MemberInfo(String memberId, String name, String tel, String nickName, String email,
      String profilePicturePath, Address address, LocalDateTime registrationDate) {
    this.memberId = memberId;
    this.name = name;
    this.tel = tel;
    this.nickName = nickName;
    this.email = email;
    this.profilePicturePath = profilePicturePath;
    this.address = address;
    this.registrationDate = registrationDate;
  }

  public MemberInfo(MemberInfoDto memberInfoDto) {
    this.memberId = memberInfoDto.getMemberId();
    this.name = memberInfoDto.getName();
    this.tel = memberInfoDto.getTel();
    this.nickName = memberInfoDto.getNickName();
    this.email = memberInfoDto.getEmail();
    this.profilePicturePath = memberInfoDto.getProfilePicturePath();
    this.address = new Address(memberInfoDto.getRoadAddress(), memberInfoDto.getDetailAddress());
    this.registrationDate = memberInfoDto.getRegistrationDate();
  }
}
