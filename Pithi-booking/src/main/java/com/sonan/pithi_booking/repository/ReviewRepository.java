package com.sonan.pithi_booking.repository;

import com.sonan.pithi_booking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
