package com.ticket.MovieBookingApp.service;

import com.ticket.MovieBookingApp.entity.Booking;
import com.ticket.MovieBookingApp.entity.BookingStatus;
import com.ticket.MovieBookingApp.entity.Show;
import com.ticket.MovieBookingApp.entity.User;
import com.ticket.MovieBookingApp.movieDTO.BookingDTO;
import com.ticket.MovieBookingApp.repository.BookingRepository;
import com.ticket.MovieBookingApp.repository.ShowRepository;
import com.ticket.MovieBookingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public  Booking createBooking(BookingDTO bookingDTO) {
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(()->new RuntimeException("Show not found"));

        if(isSeatsAvailable(show.getId(),bookingDTO.getNumberOfSeats())){
            throw new RuntimeException("Not enough seats are available");
        }

        if(bookingDTO.getSeatNumbers().size()!= bookingDTO.getNumberOfSeats()){
            throw new RuntimeException("seat Numbers and Number of seats must be equal");
        }

        validateDuplicateSeats(show.getId(),bookingDTO.getSeatNumbers());

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatNumbers(bookingDTO.getSeatNumbers());
        booking.setPrice(calculateTotalAmount(show.getPrice(),bookingDTO.getNumberOfSeats()));
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);


    }

    private Double calculateTotalAmount(Double price, Integer numberOfSeats) {
        return price*numberOfSeats;
    }

    private void validateDuplicateSeats(Long showid, List<String> seatNumbers) {
        Show show = showRepository.findById(showid)
                .orElseThrow(()->new RuntimeException("Show not found"));
        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(b->b.getBookingStatus()!= BookingStatus.CANCELLED)
                .flatMap(b->b.getSeatNumbers().stream())
                .collect(Collectors.toSet());
        List<String> duplicateSeats = seatNumbers.stream()
                .filter(occupiedSeats::contains)
                .collect(Collectors.toList());
        if(!duplicateSeats.isEmpty()){
            throw new RuntimeException("Seats are already booked");
        }
    }

    private boolean isSeatsAvailable(Long showid, Integer numberOfSeats) {
        Show show = showRepository.findById(showid)
                .orElseThrow(()->new RuntimeException("Show not found"));
        int bookedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus()!= BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return (show.getTheater().getTheaterCapacity()-bookedSeats)>=numberOfSeats;
    }
}
