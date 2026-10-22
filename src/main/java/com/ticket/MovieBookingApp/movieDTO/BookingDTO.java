package com.ticket.MovieBookingApp.movieDTO;

import com.ticket.MovieBookingApp.entity.BookingStatus;
import lombok.Data;

import java.util.List;

import java.time.LocalDateTime;

@Data
public class BookingDTO {

    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;
    private List<String> seatNumbers;
    private Long userId;
    private Long showId;

}
