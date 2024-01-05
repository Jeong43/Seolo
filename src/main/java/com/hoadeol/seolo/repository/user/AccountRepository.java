package com.hoadeol.seolo.repository.user;

import com.hoadeol.seolo.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
