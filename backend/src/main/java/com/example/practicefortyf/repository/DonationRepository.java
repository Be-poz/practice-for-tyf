package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Donation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select d from Donation d join fetch d.creator c join fetch c.account")
    List<Donation> findDonationsWithCreator();
}
