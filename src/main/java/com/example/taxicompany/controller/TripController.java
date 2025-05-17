package com.example.taxicompany.controller;

import com.example.taxicompany.entity.Trip;
import com.example.taxicompany.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "account";
    }

    @GetMapping("/trips")
    public String showTrips(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("trips", tripService.getAllTrips());
        return "account";
    }

    @PostMapping("/trips/submit")
    public String submitTrip(@RequestParam("tripId") Long tripId) {
        tripService.submitTrip(tripId);
        return "redirect:/account";
    }
}