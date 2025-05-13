package com.example.taxicompany.repository;


import com.example.taxicompany.entity.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{
    boolean existsCarByNumberPlate(String numberPlate);
}
