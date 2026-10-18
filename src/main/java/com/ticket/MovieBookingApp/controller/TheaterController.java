package com.ticket.MovieBookingApp.controller;

import com.ticket.MovieBookingApp.entity.Theater;
import com.ticket.MovieBookingApp.movieDTO.TheaterDTO;
import com.ticket.MovieBookingApp.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theater")
public class TheaterController
{
    @Autowired
    TheaterService theaterService;

    @PostMapping("addtheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO){
        return ResponseEntity.ok(theaterService.addTheater(theaterDTO));
    }

    @GetMapping("/gettheaterbylocation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location){
        return ResponseEntity.ok(theaterService.getTheaterByLocation(location));
    }

    @PutMapping("updatetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id,@RequestBody TheaterDTO theaterDTO){
        return ResponseEntity.ok(theaterService.updateTheater(id,theaterDTO));
    }

    @DeleteMapping("/deletetheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
        return ResponseEntity.ok().build();
    }

}
