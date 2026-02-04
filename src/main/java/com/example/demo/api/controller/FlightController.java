package com.example.demo.api.controller;

import com.example.demo.api.model.Flight;
import com.example.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }
}