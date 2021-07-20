package com.example.practicefortyf.repository;

import com.example.practicefortyf.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
}
