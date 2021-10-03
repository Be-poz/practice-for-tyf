package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Master;
import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.domain.Slave;
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
}