package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {

    Optional<List<Show>> findByMovieId(Long movieid);

    Optional<List<Show>> findByTheaterId(Long theaterid);
}
