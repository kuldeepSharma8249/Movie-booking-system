package com.ticket.MovieBookingApp.service;

import com.ticket.MovieBookingApp.controller.TheaterController;
import com.ticket.MovieBookingApp.entity.Theater;
import com.ticket.MovieBookingApp.movieDTO.TheaterDTO;
import com.ticket.MovieBookingApp.repository.TheaterRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    public  Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater= new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {
        Optional<List<Theater>> listOfTheaterBox = theaterRepository.findByLocation(location);
        if(listOfTheaterBox.isPresent()){
            return listOfTheaterBox.get();
        }
        else  throw new RuntimeException("No Theater found for the location entered "+location);
    }

    public  Theater updateTheater(Long id, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id).orElseThrow(()->new RuntimeException("No Theater found for the id "+id));
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
