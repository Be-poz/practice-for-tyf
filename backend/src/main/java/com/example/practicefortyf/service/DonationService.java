package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    public Donation createDonation(Member donator, Member creator) {
        Donation donation = new Donation(1000L);
        donator.addDonatedDonation(donation);
        creator.addGivenDonation(donation);
        return donationRepository.save(donation);
    }

    public Donation findDonation(Long id) {
        return donationRepository.findById(id)
                .orElseThrow();
    }

    public String lazyTest(Long id) {
        Donation byId = donationRepository.findById(id).orElseThrow();
        System.out.println("asdf");
        String name = byId.getDonator().getName();
        System.out.println("qwer");
        return name;
    }
}
