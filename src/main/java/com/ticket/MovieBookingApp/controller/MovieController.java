package com.ticket.MovieBookingApp.controller;

import com.ticket.MovieBookingApp.entity.Movie;
import com.ticket.MovieBookingApp.movieDTO.MovieDTO;
import com.ticket.MovieBookingApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/getmoviesbygenre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre){
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }
    @GetMapping("/getmoviesbylanguage")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@RequestParam String language){
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }

    @GetMapping("/getmoviesbytitle")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@RequestParam String title){
        return ResponseEntity.ok(movieService.getMoviesByTitle(title));
    }

    @PostMapping("/addmovie")
    @PreAuthorize("haseRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.addMovie(movieDTO));
    }

    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("haseRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id , @RequestBody MovieDTO movieDTO){
        return ResponseEntity.ok(movieService.updateMovie(id,movieDTO));
    }

    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("haseRole('ADMIN')")
    public ResponseEntity<Void> updateMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
