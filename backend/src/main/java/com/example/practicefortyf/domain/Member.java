package com.example.practicefortyf.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Donation> donations = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public void addDonation(Donation donation) {
        donations.add(donation);
        donation.setMember(this);
    }
}
