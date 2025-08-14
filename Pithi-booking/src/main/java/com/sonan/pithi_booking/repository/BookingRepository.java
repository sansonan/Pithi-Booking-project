package com.sonan.pithi_booking.repository;

import com.sonan.pithi_booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
//    List<Booking> findByCustomerId(Long customerId);
//    List<Booking> findByStatus(BookingStatus status);


}