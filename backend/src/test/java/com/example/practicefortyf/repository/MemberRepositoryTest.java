package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    EntityManager em;

    @BeforeEach
    void setUp() {
        Donation donation = new Donation(1000L);
        Member member = new Member("bepoz");
        member.addDonation(donation);

        memberRepository.save(member);

        em.clear();
        em.flush();
    }

    @Test
    @DisplayName("jpql join test")
    public void jpqlJoinTest() {


        Member byId = memberRepository.findById(1L).get();
        System.out.println(byId.getDonations().size());
        System.out.println("asdf");
    }

}