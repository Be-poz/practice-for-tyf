package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DonationRepository donationRepository;
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
        Donation donation2 = new Donation(2000L);
        donation.setCreator(creator);
        donation.setDonator(donator);
        donation2.setCreator(donator);
        donation2.setDonator(creator);
        em.persist(donation);
        em.persist(donation2);

        em.flush();
        em.clear();

        Member findMember = memberRepository.getById(creator.getId());
        System.out.println(findMember.getName());
        System.out.println("asdf");

    }
}