package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.dto.DonationResponse;
import com.example.practicefortyf.repository.DonationRepository;
import com.example.practicefortyf.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DonationServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DonationService donationService;

    @Autowired
    private EntityManager em;

    @Test
    public void createDonationTest() {
        memberRepository.save(new Member("member"));
        DonationResponse donation = donationService.createDonation("member", 1000L);
        assertThat(donation.getId()).isNull();
    }
}