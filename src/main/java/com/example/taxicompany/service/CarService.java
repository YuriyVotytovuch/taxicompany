package com.example.taxicompany.service;

import com.example.taxicompany.entity.Car;
import com.example.taxicompany.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car saveCar(Car car) {
        String username = getCurrentUsername();
        if ("anonymousUser".equals(username)) {
            throw new SecurityException("User must be authenticated to register a car");
        }
        car.setRiderLogin(username);
        return carRepository.save(car);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }
}