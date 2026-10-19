package com.ticket.MovieBookingApp.service;

import com.ticket.MovieBookingApp.entity.Booking;
import com.ticket.MovieBookingApp.entity.Movie;
import com.ticket.MovieBookingApp.entity.Show;
import com.ticket.MovieBookingApp.entity.Theater;
import com.ticket.MovieBookingApp.movieDTO.ShowDTO;
import com.ticket.MovieBookingApp.repository.MovieRepository;
import com.ticket.MovieBookingApp.repository.ShowRepository;
import com.ticket.MovieBookingApp.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public  Show createShow(ShowDTO showDTO) {

        Movie movie = movieRepository.findById(showDTO.getMovieId()).
                orElseThrow(()->new RuntimeException("No movie found for id "+showDTO.getMovieId()));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId()).
                orElseThrow(()->new RuntimeException("No theater found for id "+showDTO.getTheaterId()));

        Show show= new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setTheater(theater);
        show.setMovie(movie);
        return showRepository.save(show);
    }


    public  List<Show> getAllShows() {
        return showRepository.findAll();
    }


    public List<Show> getShowsByMovie(Long movieid) {
        Optional<List<Show>> showListBox = showRepository.findByMovieId(movieid);
        if(showListBox.isPresent()){
            return showListBox.get();
        }
        else throw new RuntimeException("No show found by this movieid "+movieid);
    }


    public  List<Show> getShowsByTheater(Long theaterid) {
        Optional<List<Show>> showListBox = showRepository.findByTheaterId(theaterid);
        if(showListBox.isPresent()){
            return showListBox.get();
        }
        else throw new RuntimeException("NO show found by this theaterid "+theaterid);
    }

    public  Show updateShow(Long id, ShowDTO showDTO) {
        Show show = showRepository.findById(id).
                orElseThrow(()->new RuntimeException("No show found with this id "+id));

        Movie movie = movieRepository.findById(showDTO.getMovieId()).
                orElseThrow(()->new RuntimeException("No movie found for id "+showDTO.getMovieId()));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId()).
                orElseThrow(()->new RuntimeException("No theater found for id "+showDTO.getTheaterId()));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        return showRepository.save(show);
    }


    public void deleteShow(Long id) {

        if(!showRepository.existsById(id)){
            throw new RuntimeException("No show available for the id "+id);
        }

        List<Booking> bookings = showRepository.findById(id).get().getBookings();
        if(bookings.isEmpty()){
            throw new RuntimeException("Can't delete show with existing bookings ");
        }
        else showRepository.deleteById(id);
    }


}
