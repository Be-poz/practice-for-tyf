package com.example.practicefortyf.service;

import com.example.practicefortyf.domain.Donation;
import com.example.practicefortyf.domain.Member;
import com.example.practicefortyf.dto.DonationResponse;
import com.example.practicefortyf.repository.DonationRepository;
import com.example.practicefortyf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {

    private final MemberRepository memberRepository;
    private final DonationRepository donationRepository;

    public DonationResponse createDonation(String name, Long amount) {
        Member findMember = memberRepository.findByName(name);
        Donation donation = new Donation(amount);
        // Donation savedDonation = donationRepository.save(donation);
        findMember.addDonation(donation);
//        donationRepository.flush();

        return new DonationResponse(donation);
    }
}
