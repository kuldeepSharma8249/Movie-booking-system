package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Booking;
import com.ticket.MovieBookingApp.entity.BookingStatus;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(Long userid);
    List<Booking> findByShowId(Long showid);
    List<Booking> findBookingByStatus(BookingStatus bookingStatus);
}
