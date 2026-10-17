package com.ticket.MovieBookingApp.service;

import com.ticket.MovieBookingApp.entity.Movie;
import com.ticket.MovieBookingApp.movieDTO.MovieDTO;
import com.ticket.MovieBookingApp.repository.MovieRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public  Movie addMovie(MovieDTO movieDTO) {
       Movie movie=new Movie();
       movie.setName(movieDTO.getName());
       movie.setDescription(movieDTO.getDescription());
       movie.setGenre(movieDTO.getGenre());
       movie.setReleaseDate(movieDTO.getReleaseDate());
       movie.setDuration(movieDTO.getDuration());
       movie.setLanguage(movieDTO.getLanguage());
       return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public  List<Movie> getMoviesByGenre(String genre) {
        Optional<List<Movie>> ListofMovieBox = movieRepository.findByGenre(genre);
        if(ListofMovieBox.isPresent()){
            return ListofMovieBox.get();
        }else {
            throw new RuntimeException("No movies found with genre " +genre);
        }
    }

    public  List<Movie> getMoviesByLanguage(String language) {
        Optional<List<Movie>> ListofMovieBox = movieRepository.findByLanguage(language);
        if(ListofMovieBox.isPresent()){
            return ListofMovieBox.get();
        }else {
            throw new RuntimeException("No movies found of Language " + language);
        }
    }

    public  List<Movie> getMoviesByTitle(String title) {
        Optional<List<Movie>> movieBox = movieRepository.findByName(title);
        if(movieBox.isPresent()){
            return movieBox.get();
        }else {
            throw new RuntimeException("No movies found of Title " + title);
        }
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie= movieRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No Movie found for the id "+id));
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setLanguage(movieDTO.getLanguage());
        return movieRepository.save(movie);
    }


    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
