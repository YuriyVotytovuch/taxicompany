package com.example.taxicompany.service;

import com.example.taxicompany.entity.Trip;
import com.example.taxicompany.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip trip) {
        String username = getCurrentUsername();
        trip.setActive(true);
        trip.setStatus("Очікує водія");
        trip.setUserName(username);
        return tripRepository.save(trip);
    }
    public Iterable<Trip> getAllTrips() {
        return tripRepository.findAllActiveTrips();
    }
    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
    public Trip submitTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with ID: " + tripId));
        trip.setDriverName(getCurrentUsername());
        trip.setStatus("В дорозі");
        trip.setActive(false);
        return tripRepository.save(trip);
    }
}