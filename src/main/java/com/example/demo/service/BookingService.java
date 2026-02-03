package com.example.demo.service;

import com.example.demo.api.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    // This method replaces your "showBookings()" logic
    public List<Booking> getPassengerBookings(int passengerId) {
        return bookingRepository.findByPassengerId(passengerId);
    }

    /**
     * Replaces the addBooking() logic from Project 2.
     * The @Transactional annotation ensures that if ANY part fails,
     * the database rolls back (capacity isn't decreased if booking fails).
     */
    @Transactional
    public Booking bookFlight(int passengerId, int flightId, String date) {
        // 1. Fetch the Passenger
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger with ID " + passengerId + " not found"));

        // 2. Fetch the Flight
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight with ID " + flightId + " not found"));

        // 3. Check Capacity (The core logic from your Console App)
        if (flight.getCapacity() <= 0) {
            throw new RuntimeException("Booking failed: Flight " + flight.getFlightNumber() + " is full.");
        }

        // 4. Update Capacity
        flight.setCapacity(flight.getCapacity() - 1);
        flightRepository.save(flight); // JPA updates the existing record

        // 5. Create and Save the Booking
        // Using the constructor we fixed in the previous step
        OneWayBooking newBooking = new OneWayBooking(passenger, flight, date);

        return bookingRepository.save(newBooking);
    }
}