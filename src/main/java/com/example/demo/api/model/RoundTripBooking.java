package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ROUND_TRIP")
@Getter @Setter @NoArgsConstructor
public class RoundTripBooking extends Booking {

    @ManyToOne
    @JoinColumn(name = "outbound_flight_id")
    private Flight outboundFlight;

    @ManyToOne
    @JoinColumn(name = "return_flight_id")
    private Flight returnFlight;
}