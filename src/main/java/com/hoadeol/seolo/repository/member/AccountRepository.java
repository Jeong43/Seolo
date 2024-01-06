package com.hoadeol.seolo.repository.member;

import com.hoadeol.seolo.model.member.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
