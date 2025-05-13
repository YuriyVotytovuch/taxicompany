package com.example.taxicompany.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taxicompany.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByUsername(String username);
}
