package com.example.taxicompany.service;

import com.example.taxicompany.entity.Car;
import com.example.taxicompany.entity.User;
import com.example.taxicompany.repository.CarRepository;
import com.example.taxicompany.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public AdminService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Car> getAllRequests() {
        logger.info("Fetching all active driver requests");
        return carRepository.findAllByActive(true); // Повертаємо активні запити
    }

    public void approveDriver(Long carId) {
        logger.info("Approving driver for car ID: {}", carId);
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Автомобіль не знайдено"));
        User user = userRepository.findByUsername(car.getRiderLogin())
                .orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено"));
        user.setRole("DRIVER");
        userRepository.save(user);
        // Після затвердження заявка вважається обробленою
        car.setActive(false); // Або видалити: carRepository.delete(car);
        carRepository.save(car);
        logger.info("Driver approved for car ID: {}", carId);
    }

    public void rejectDriver(Long carId) {
        logger.info("Rejecting driver for car ID: {}", carId);
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Автомобіль не знайдено"));
        car.setActive(false); // Відхиляємо заявку
        carRepository.save(car);
        logger.info("Driver rejected for car ID: {}", carId);
    }
}