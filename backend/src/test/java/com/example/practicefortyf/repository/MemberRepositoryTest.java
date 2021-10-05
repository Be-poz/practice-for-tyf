package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.*;
import com.example.practicefortyf.service.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    MasterRepository masterRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    DonationService donationService;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Two Relation Test")
    public void twoRelationTest() {
        Member creator = new Member("creator");
        Member donator = new Member("donator");
        em.persist(creator);
        em.persist(donator);

        Donation donation = new Donation(1000L);
        donation.setCreator(creator);
        donation.setDonator(donator);
        em.persist(donation);

        em.flush();
        em.clear();

        Member findCreator = memberRepository.findById(creator.getId()).get();
        System.out.println("***");
        List<Donation> givenDonations = findCreator.getGivenDonations();
        System.out.println("&&&");
        System.out.println(givenDonations.contains(donation));
        System.out.println("###");
    }

    @Test
    public void cascadeTest() {
        Master master = new Master("master");
        Slave slave = new Slave("slave");
        master.setSlave(slave);

        masterRepository.save(master);
        em.flush();
        em.clear();

        Master findMaster = masterRepository.getById(master.getId());
        findMaster.getSlave().setName("asdf");
        em.flush();
        em.clear();
        System.out.println("asdf");
    }

    @Test
    public void lazyTwiceTest() {
        Account account = new Account("brandnew-account");
        Account account2 = new Account("brandnew-account");
        Account account3 = new Account("brandnew-account");
        Account account4 = new Account("brandnew-account");
        Account account5 = new Account("brandnew-account");
        Account account6 = new Account("brandnew-account");
        Account account7 = new Account("brandnew-account");
        Account account8 = new Account("brandnew-account");
        Account account9 = new Account("brandnew-account");
        Account account10 = new Account("brandnew-account");
        Account account11 = new Account("brandnew-account");
        Account account12 = new Account("brandnew-account");
        Account account13 = new Account("brandnew-account");
        Account account14 = new Account("brandnew-account");
        Account account15 = new Account("brandnew-account");
        Account account16 = new Account("brandnew-account");
        accountRepository.save(account);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);
        accountRepository.save(account5);
        accountRepository.save(account6);
        accountRepository.save(account7);
        accountRepository.save(account8);
        accountRepository.save(account9);
        accountRepository.save(account10);
        accountRepository.save(account11);
        accountRepository.save(account12);
        accountRepository.save(account13);
        accountRepository.save(account14);
        accountRepository.save(account15);
        accountRepository.save(account16);
        Member donator = new Member(account, "donator");
        Member creator = new Member(account2, "creator");
        Member a = new Member(account3, "a");
        Member b = new Member(account4, "b");
        Member c = new Member(account5, "c");
        Member d = new Member(account6, "d");
        Member e = new Member(account7, "e");
        Member f = new Member(account8, "f");
        Member g = new Member(account9, "g");
        Member h = new Member(account10, "h");
        Member i = new Member(account11, "i");
        Member j = new Member(account12, "j");
        Member k = new Member(account13, "k");
        Member l = new Member(account14, "l");
        Member m = new Member(account15, "m");
        Member n = new Member(account16, "n");
        memberRepository.save(donator);
        memberRepository.save(creator);
        memberRepository.save(a);
        memberRepository.save(b);
        memberRepository.save(c);
        memberRepository.save(d);
        memberRepository.save(e);
        memberRepository.save(f);
        memberRepository.save(g);
        memberRepository.save(h);
        memberRepository.save(i);
        memberRepository.save(j);
        memberRepository.save(k);
        memberRepository.save(l);
        memberRepository.save(m);
        memberRepository.save(n);
        Donation donation1 = new Donation(1000L, "message");
        Donation donation2 = new Donation(1000L, "message");
        Donation donation3 = new Donation(1000L, "message");
        Donation donation4 = new Donation(1000L, "message");
        Donation donation5 = new Donation(1000L, "message");
        Donation donation6 = new Donation(1000L, "message");
        Donation donation7 = new Donation(1000L, "message");
        Donation donation8 = new Donation(1000L, "message");

        donation1.from(donator);
        donation1.to(creator);
        donation2.from(a);
        donation2.to(b);
        donation3.from(c);
        donation3.to(d);
        donation4.from(e);
        donation4.to(f);
        donation5.from(g);
        donation5.to(h);
        donation6.from(i);
        donation6.to(j);
        donation7.from(k);
        donation7.to(l);
        donation8.from(m);
        donation8.to(n);
        donationRepository.save(donation1);
        donationRepository.save(donation2);
        donationRepository.save(donation3);
        donationRepository.save(donation4);
        donationRepository.save(donation5);
        donationRepository.save(donation6);
        donationRepository.save(donation7);
        donationRepository.save(donation8);
        em.flush();
        em.clear();

        List<Donation> donations = donationRepository.findDonationsWithCreator();
        System.out.println("*********************");

        for (Donation donation : donations) {
            System.out.println(donation.getCreator().getAccount().getName());
        }
    }
}