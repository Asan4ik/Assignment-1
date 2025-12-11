void main() {
    // Create a Passenger
    Passenger passenger = new Passenger(1, "Asanali", "Serik", "aserik738@gmail.com");

    // Create a Flight
    Flight flight = new Flight(101, "KA123", "Almaty", "Astana",
            "2025-12-15 08:00", "2025-12-15 10:00", 180);

    // Create a Booking
    Booking booking = new Booking(5001, flight, passenger, "2025-12-11");

    // Print Passenger Info
    System.out.println("Passenger Information:");
    System.out.println("ID: " + passenger.getPassengerId());
    System.out.println("Name: " + passenger.getFirstName() + " " + passenger.getLastName());
    System.out.println("Email: " + passenger.getEmail());
    System.out.println();

    // Print Flight Info
    System.out.println("Flight Information:");
    System.out.println("Flight ID: " + flight.getFlightId());
    System.out.println("Flight Number: " + flight.getFlightNumber());
    System.out.println("From: " + flight.getOrigin() + " To: " + flight.getDestination());
    System.out.println("Departure: " + flight.getDepartureTime() + " Arrival: " + flight.getArrivalTime());
    System.out.println("Capacity: " + flight.getCapacity());
    System.out.println();

    // Print Booking Info
    System.out.println("Booking Information:");
    System.out.println("Booking ID: " + booking.getBookingId());
    System.out.println("Passenger: " + booking.getPassenger().getFirstName() + " " +
            booking.getPassenger().getLastName());
    System.out.println("Flight Number: " + booking.getFlight().getFlightNumber());
    System.out.println("Booking Date: " + booking.getBookingDate());
}
