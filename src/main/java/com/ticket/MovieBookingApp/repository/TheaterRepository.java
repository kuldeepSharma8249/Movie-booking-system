package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
}
