package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
