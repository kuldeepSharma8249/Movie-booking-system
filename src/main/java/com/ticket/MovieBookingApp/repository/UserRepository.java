package com.ticket.MovieBookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticket.MovieBookingApp.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
