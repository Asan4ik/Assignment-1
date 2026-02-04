package com.example.demo.api.controller;

import com.example.demo.repository.FlightRepository;    // 1. Add this import
import com.example.demo.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/")
    public String showIndex(Model model) {
        // Fetch counts to show on the dashboard
        model.addAttribute("passengerCount", passengerRepository.count());
        model.addAttribute("flightCount", flightRepository.count());
        // Note: If you have a bookingRepository, you can add that count too!
        return "index";
    }

    @GetMapping("/view-passengers")
    public String viewPassengers(Model model) {
        model.addAttribute("passengers", passengerRepository.findAll());
        return "passengers";
    }

    @GetMapping("/view-flights")
    public String viewFlights(Model model) {
        // 3. This will now work!
        model.addAttribute("flights", flightRepository.findAll());
        return "flights";
    }

    @GetMapping("/book-flight-ui")
    public String bookingForm(@RequestParam(required = false) Integer flightId, Model model) {
        model.addAttribute("flightId", flightId);
        return "book-flight";
    }
}