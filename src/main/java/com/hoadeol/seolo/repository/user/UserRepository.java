package com.hoadeol.seolo.repository.user;

import com.hoadeol.seolo.model.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByUserInfoUserId(String userId);

  List<User> findByUserInfoNickName(String nickName);

  List<User> findByUserInfoTel(String tel);

}
