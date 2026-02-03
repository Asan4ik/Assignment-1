package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "booking_type")
@Getter @Setter @NoArgsConstructor // Lombok handles empty constructor for JPA
public abstract class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private String bookingDate;

    // Add this constructor for the subclasses to call via super()
    public Booking(Passenger passenger, String bookingDate) {
        this.passenger = passenger;
        this.bookingDate = bookingDate;
    }
}