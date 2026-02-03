package com.example.demo.repository;

import com.example.demo.api.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    // Spring will automatically implement basic CRUD (find, save, delete)
    Passenger findByEmail(String email);
}