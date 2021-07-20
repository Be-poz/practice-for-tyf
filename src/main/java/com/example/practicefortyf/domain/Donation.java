package com.example.practicefortyf.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Donation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Donation(Long amount) {
        this.amount = amount;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
