package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
