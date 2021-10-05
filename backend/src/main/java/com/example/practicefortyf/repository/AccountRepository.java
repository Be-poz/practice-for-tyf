package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
