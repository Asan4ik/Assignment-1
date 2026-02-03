package com.example.demo.api.controller;

import com.example.demo.api.model.Passenger;
import com.example.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/passenger")
    public Passenger getPassenger(@RequestParam int id) {
        Optional<Passenger> passenger = passengerService.getPassenger(id);
        if(passenger.isPresent()) {
            return (Passenger) passenger.get();
        }
        return null;
    }
}
