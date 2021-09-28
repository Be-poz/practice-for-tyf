package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Master;
import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.domain.Slave;
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
        Member jpas = memberService.createMember("dani");

        Donation donation = donationService.createDonation(bepoz, jpas);

        em.flush();
        em.clear();

        System.out.println(donationService.lazyTest(donation.getId()));
    }

    @Test
    public void masterSlave() {
        Master master = new Master("master");
        Slave slave = new Slave("slave");

        em.persist(slave);
        master.setSlave(slave);
        em.persist(master);

        em.flush();
        em.clear();
        System.out.println("asdf");
        Master findMaster = em.find(Master.class, master.getId());
        System.out.println("asdf");
        String name = findMaster.getSlave().getName();
        System.out.println("asdf");
        System.out.println(name);
    }
}