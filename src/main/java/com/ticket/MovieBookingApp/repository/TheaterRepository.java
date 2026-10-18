package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater,Long> {

    Optional<List<Theater>> findByLocation(String location);
}
