package com.hoadeol.seolo.repository.member;

import com.hoadeol.seolo.model.member.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  List<Member> findByMemberInfoMemberId(String userId);

  List<Member> findByMemberInfoNickName(String nickName);

  List<Member> findByMemberInfoTel(String tel);

}
