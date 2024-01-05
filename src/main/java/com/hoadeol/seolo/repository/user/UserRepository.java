package com.hoadeol.seolo.repository.user;

import com.hoadeol.seolo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
