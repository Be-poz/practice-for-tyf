package com.example.practicefortyf.dto;

import com.example.practicefortyf.domain.Donation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DonationResponse {

    private Long id;
    private Long amount;

    public DonationResponse(Donation donation) {
        this.id = donation.getId();
        this.amount = donation.getAmount();
    }
}
