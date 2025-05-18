package com.example.taxicompany.controller;

import com.example.taxicompany.entity.Car;
import com.example.taxicompany.entity.Trip;
import com.example.taxicompany.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/trips")
    public String createTrip(@ModelAttribute Trip trip) {
        tripService.createTrip(trip);
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String getAccountPage(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("trips", tripService.getAllTrips());
        model.addAttribute("car", new Car());
        return "account";
    }

    @GetMapping("/trips")
    public String showTrips(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("trips", tripService.getAllTrips());
        model.addAttribute("car", new Car());
        return "account";
    }

    @PostMapping("/trips/accept")
    public String acceptTrip(@RequestParam("tripId") Long tripId, Model model) {
        Trip trip = tripService.acceptTrip(tripId);
        model.addAttribute("trip", new Trip());
        model.addAttribute("trips", tripService.getAllTrips());
        model.addAttribute("selectedTrip", trip);
        return "account";
    }

    @PostMapping("/trips/complete")
    public String completeTrip(@RequestParam("tripId") Long tripId) {
        tripService.completeTrip(tripId);
        return "redirect:/account";
    }

    @PostMapping("/trips/cancel")
    public String cancelTrip(@RequestParam("tripId") Long tripId) {
        tripService.cancelTrip(tripId);
        return "redirect:/account";
    }
    @GetMapping("/api/trips/available")
    @ResponseBody
    public boolean tripsAvailable() {
        return tripService.getAllTrips().iterator().hasNext();
    }
}