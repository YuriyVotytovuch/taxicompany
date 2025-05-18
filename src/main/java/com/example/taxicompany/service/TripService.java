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

    public Trip acceptTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Такого нема: " + tripId));
        if (!trip.getActive()) {
            throw new IllegalStateException("Заявку відмінили, або поїздку уже забронював інший водій");
        }
        trip.setDriverName(getCurrentUsername());
        trip.setStatus("В дорозі");
        return tripRepository.save(trip);
    }

    public Trip completeTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Нема такого " + tripId));
        trip.setStatus("Виконано");
        trip.setActive(false);
        return tripRepository.save(trip);
    }

    public Trip cancelTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("нема такого " + tripId));
        trip.setStatus("Скасовано");
        trip.setDriverName(null);
        return tripRepository.save(trip);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
    public Iterable<Trip> getAvailableTrips() {
        return tripRepository.findAllActiveTrips();
    }
      public Trip getSelectedTrip() {
        return tripRepository.findAllActiveTrips().iterator().next();
    }
}