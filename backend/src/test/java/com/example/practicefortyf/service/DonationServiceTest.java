package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Member;
import org.apache.juli.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DonationServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private EntityManager em;

    @Test
    public void test() {
        Member bepoz = memberService.createMember("bepoz");
        Member dani = memberService.createMember("dani");

        Donation donation = donationService.createDonation(bepoz, dani);

        em.flush();
        em.clear();

        Donation donation1 = donationService.findDonation(donation.getId());
        System.out.println("asdf");
        Member creator = donation1.getCreator();
        System.out.println("asdf");
        System.out.println(creator.getName());
        System.out.println("asdf");
        System.out.println(donation1.getCreator().getName());
        System.out.println("asdf");
        System.out.println(donation1.getDonator().getName());
        System.out.println("asdf");
    }

}