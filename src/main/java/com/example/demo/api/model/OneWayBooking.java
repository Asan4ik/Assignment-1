package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ONE_WAY")
@Getter @Setter @NoArgsConstructor
public class OneWayBooking extends Booking {

    @ManyToOne
    @JoinColumn(name = "outbound_flight_id")
    private Flight flight;

    public OneWayBooking(Passenger passenger, Flight flight, String date) {
        // Calls the constructor we just added to Booking.java
        super(passenger, date);
        this.flight = flight;
    }
}