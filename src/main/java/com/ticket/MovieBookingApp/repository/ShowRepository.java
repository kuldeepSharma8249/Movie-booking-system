package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {
}
