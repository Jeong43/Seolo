package com.hoadeol.seolo.model.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class WithdrawnMember {

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne
  @JoinColumn(name = "ACCOUNT_ID")
  private Account account;
  private MemberInfo memberInfo;
  private WithdrawnType withdrawnType;
  private String comment;
  private LocalDateTime withdrawnDate;

  public static WithdrawnMember createWithdrawnMember
      (Member member, WithdrawnType withdrawnType, String comment) {
    WithdrawnMember withdrawnMember = new WithdrawnMember();
    withdrawnMember.setAccount(member.getAccount());
    withdrawnMember.setMemberInfo(member.getMemberInfo());
    withdrawnMember.setWithdrawnType(withdrawnType);
    withdrawnMember.setComment(comment);
    withdrawnMember.setWithdrawnDate(LocalDateTime.now());

    return withdrawnMember;
  }

}
