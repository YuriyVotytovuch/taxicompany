package com.example.taxicompany.service;

import com.example.taxicompany.entity.Trip;
import com.example.taxicompany.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip trip) {
        trip.setStatus("Очікує водія");
        trip.setActive(true);
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAllActiveTrips();
    }

    public Trip acceptTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Поїздка не знайдена"));
        trip.setStatus("Прийнята");
        return tripRepository.save(trip);
    }

    public void completeTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Поїздка не знайдена"));
        trip.setStatus("Виконана");
        trip.setActive(false);
        tripRepository.save(trip);
    }

    public Trip cancelTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Поїздка не знайдена"));
        trip.setStatus("Відмінена");
        trip.setActive(false);
        return tripRepository.save(trip);
    }

    public Trip findTripById(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Поїздка не знайдена"));
    }
}