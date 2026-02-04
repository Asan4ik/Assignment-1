package com.example.demo.service;

import com.example.demo.api.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookingService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private FlightRepository flightRepository;
    @Autowired private PassengerRepository passengerRepository;

    public List<Booking> getPassengerBookings(int passengerId) {
        return bookingRepository.findByPassengerId(passengerId);
    }

    @Transactional
    public Booking bookFlight(int passengerId, int flightId, String date) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Error: Passenger not found with ID: " + passengerId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Error: Flight not found with ID: " + flightId));

        if (flight.getCapacity() <= 0) {
            throw new RuntimeException("Booking failed: Flight " + flight.getFlightNumber() + " is full!");
        }

        flight.setCapacity(flight.getCapacity() - 1);
        flightRepository.save(flight);

        OneWayBooking newBooking = new OneWayBooking();
        newBooking.setPassenger(passenger);
        newBooking.setFlight(flight);
        newBooking.setBookingDate(date);

        return bookingRepository.save(newBooking);
    }
}