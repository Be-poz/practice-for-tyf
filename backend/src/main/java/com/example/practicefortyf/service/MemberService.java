package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(String name) {
        return memberRepository.save(new Member(name));
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow();
    }
}
