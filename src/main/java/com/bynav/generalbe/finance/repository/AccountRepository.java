package com.bynav.generalbe.finance.repository;

import com.bynav.generalbe.finance.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUserId(String userid);
}
