package com.ticket.MovieBookingApp.repository;

import com.ticket.MovieBookingApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ticket.MovieBookingApp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
