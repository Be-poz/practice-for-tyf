package com.example.practicefortyf.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"donator_id", "creator_id"})
        }
)
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donator_id")
    private Member donator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Member creator;

    public Donation(Long id, Long amount) {
        this.id = id;
        this.amount = amount;
    }

    public Donation(Long amount) {
        this.amount = amount;
    }

    public void from(Member member) {
        this.donator = member;
    }

    public void to(Member creator) {
        this.creator = creator;
    }
}
