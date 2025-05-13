package com.example.taxicompany.repository;


import com.example.taxicompany.entity.Trip;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{
    boolean existsTripByStartPointAndEndPoint(String startPoint, String endPoint);
}
