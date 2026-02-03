package com.example.demo.service;

import com.example.demo.api.model.Passenger;
import com.example.demo.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Optional<Passenger> getPassenger(int id) {
        return passengerRepository.findById(id); // Queries the DB!
    }

    public Passenger registerPassenger(Passenger passenger) {
        return passengerRepository.save(passenger); // Replaces your INSERT SQL
    }
}