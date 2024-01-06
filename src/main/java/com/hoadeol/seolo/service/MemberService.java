package com.hoadeol.seolo.service;

import com.hoadeol.seolo.exception.member.DuplicateMemberIdException;
import com.hoadeol.seolo.exception.member.DuplicateNickNameException;
import com.hoadeol.seolo.exception.member.DuplicateTelException;
import com.hoadeol.seolo.exception.member.MemberNotFoundException;
import com.hoadeol.seolo.model.member.Member;
import com.hoadeol.seolo.model.member.MemberInfo;
import com.hoadeol.seolo.model.member.WithdrawnMember;
import com.hoadeol.seolo.model.member.WithdrawnType;
import com.hoadeol.seolo.repository.member.MemberRepository;
import com.hoadeol.seolo.repository.member.WithdrawnMemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final WithdrawnMemberRepository withdrawnMemberRepository;

  /**
   * 회원 전체 조회
   */
  public List<Member> getAllUsers() {
    return memberRepository.findAll();
  }

  /**
   * 회원 검색
   */
  public Member getUserById(Long id) {
    return memberRepository.findById(id).orElse(null);
  }

  /**
   * 회원가입
   */
  @Transactional
  public Long join(Member member) {
    MemberInfo memberInfo = member.getMemberInfo();
    validateDuplicateUserId(memberInfo.getMemberId());
    validateDuplicateUserInfo(memberInfo);

    memberRepository.save(member);
    return member.getId();
  }

  /**
   * 회원아이디 중복 검증
   */
  private void validateDuplicateUserId(String memberId) {
    List<Member> byUserId = memberRepository.findByMemberInfoMemberId(memberId);
    if (!byUserId.isEmpty()) {
      throw new DuplicateMemberIdException(memberId);
    }
  }

  /**
   * 회원정보 중복 검증
   */
  private void validateDuplicateUserInfo(MemberInfo memberInfo) {
    List<Member> byTel = memberRepository.findByMemberInfoTel(memberInfo.getTel());
    if (!byTel.isEmpty()) {
      throw new DuplicateTelException(memberInfo.getTel());
    }

    List<Member> byNickName = memberRepository.findByMemberInfoNickName(memberInfo.getNickName());
    if (!byNickName.isEmpty()) {
      throw new DuplicateNickNameException(memberInfo.getNickName());
    }
  }

  /**
   * 회원 정보 변경
   */
  @Transactional
  public Member updateUser(Long id, Member updatedMember) {
    validateDuplicateUserInfo(updatedMember.getMemberInfo());

    Member existingMember = memberRepository.findById(id).orElseThrow(
        () -> new MemberNotFoundException(id)
    );
    existingMember.setMemberInfo(updatedMember.getMemberInfo());
    existingMember.setPassword(updatedMember.getPassword());

    return memberRepository.save(existingMember);
  }

  /**
   * 회원 탈퇴
   */
  @Transactional
  public Long withdrawn(Long id, WithdrawnType withdrawnType, String comment) {
    Member member = memberRepository.findById(id).orElseThrow(
        () -> new MemberNotFoundException(id)
    );

    // 탈퇴회원 등록
    WithdrawnMember withdrawnMember
        = WithdrawnMember.createWithdrawnMember(member, withdrawnType, comment);
    withdrawnMemberRepository.save(withdrawnMember);

    // 유저 정보 삭제
    memberRepository.delete(member);

    return withdrawnMember.getId();
  }
}
