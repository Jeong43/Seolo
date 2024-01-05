package com.hoadeol.seolo.service;

import com.hoadeol.seolo.model.user.User;
import com.hoadeol.seolo.model.user.UserInfo;
import com.hoadeol.seolo.model.user.WithdrawnType;
import com.hoadeol.seolo.model.user.WithdrawnUser;
import com.hoadeol.seolo.repository.user.UserRepository;
import com.hoadeol.seolo.repository.user.WithdrawnUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final WithdrawnUserRepository withdrawnUserRepository;

  /**
   * 회원 전체 조회
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * 회원 검색
   */
  public User getById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  /**
   * 회원가입
   */
  @Transactional
  public Long join(User user) {
    UserInfo userInfo = user.getUserInfo();
    validateDuplicateUserId(userInfo.getUserId());
    validateDuplicateUserInfo(userInfo);

    userRepository.save(user);
    return user.getId();
  }

  /**
   * 회원아이디 중복 검증
   */
  private void validateDuplicateUserId(String userId) {
    List<User> byUserId = userRepository.getByUserId(userId);
    if (!byUserId.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 아이디입니다.");
    }
  }

  /**
   * 회원정보 중복 검증
   */
  private void validateDuplicateUserInfo(UserInfo userInfo) {
    List<User> byTel = userRepository.findByTel(userInfo.getTel());
    if (!byTel.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 전화번호입니다.");
    }

    List<User> byNickName = userRepository.findByNickName(userInfo.getNickName());
    if (!byNickName.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 닉네임입니다.");
    }
  }

  /**
   * 회원 정보 변경
   */
  @Transactional
  public User updateUser(Long id, User user) {
    validateDuplicateUserInfo(user.getUserInfo());

    User existingUser = userRepository.getById(id);
    if (existingUser != null) {
      existingUser.setUserInfo(user.getUserInfo());
      existingUser.setPassword(user.getPassword());
      return userRepository.save(existingUser);
    }
    return null;
  }

  /**
   * 회원 탈퇴
   */
  @Transactional
  public Long withdrawn(Long userId, WithdrawnType withdrawnType, String comment) {
    User user = userRepository.findById(userId).get();

    // 탈퇴회원 등록
    WithdrawnUser withdrawnUser = WithdrawnUser.createWithdrawnUser(user, withdrawnType, comment);
    withdrawnUserRepository.save(withdrawnUser);

    // 유저 정보 삭제
    userRepository.delete(user);

    return withdrawnUser.getId();
  }
}
