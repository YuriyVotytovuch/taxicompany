package com.example.taxicompany.repository;

import com.example.taxicompany.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    boolean existsTripByFromStreetAndToStreet(String fromStreet, String toStreet);
}