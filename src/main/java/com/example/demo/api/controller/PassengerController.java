package com.example.demo.api.controller;

import com.example.demo.api.model.Passenger;
import com.example.demo.repository.PassengerRepository; // 1. Check this import!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @GetMapping("/all")
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @GetMapping
    public Passenger getPassenger(@RequestParam int id) {
        return passengerRepository.findById(id).orElse(null);
    }
}