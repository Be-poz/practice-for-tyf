package com.example.practicefortyf.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "donator")
    private List<Donation> donatedDonations = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Donation> givenDonations = new ArrayList<>();

    private String name;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String name) {
        this.name = name;
    }

    public void addDonatedDonation(Donation donation) {
        donatedDonations.add(donation);
        donation.from(this);
    }

    public void addGivenDonation(Donation donation) {
        givenDonations.add(donation);
        donation.to(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
