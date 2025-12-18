import java.util.List;
import java.util.ArrayList;

public class Passenger {

    private int passengerId;
    private String firstName;
    private String lastName;
    private String email;

    // New field to track passenger bookings
    private final List<Booking> bookings = new ArrayList<>();

    public Passenger() {
    }

    public Passenger(int passengerId, String firstName, String lastName, String email) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // ============================
    //     BOOK A FLIGHT
    // ============================
    public Booking bookFlight(Flight flight, int bookingId, String bookingDate) {
        if (flight == null) {
            System.out.println("Cannot book: flight is null.");
            return null;
        }

        if (flight.getCapacity() <= 0) {
            System.out.println("Cannot book: no seats available for flight " + flight.getFlightNumber());
            return null;
        }

        // Check if already booked
        for (Booking b : bookings) {
            if (b.getFlight().getFlightId() == flight.getFlightId()) {
                System.out.println("Passenger already booked this flight.");
                return null;
            }
        }

        // Create booking
        Booking booking = new Booking(bookingId, flight, this, bookingDate);
        bookings.add(booking);

        // Decrease available capacity
        flight.setCapacity(flight.getCapacity() - 1);

        System.out.println("Flight " + flight.getFlightNumber() + " booked successfully.");
        return booking;
    }

    // ============================
    //     CANCEL A BOOKING
    // ============================
    public boolean cancelBooking(int bookingId) {
        Booking target = null;

        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                target = b;
                break;
            }
        }

        if (target == null) {
            System.out.println("Cannot cancel: booking not found.");
            return false;
        }

        // Restore flight capacity
        Flight flight = target.getFlight();
        flight.setCapacity(flight.getCapacity() + 1);

        bookings.remove(target);

        System.out.println("Booking " + bookingId + " cancelled successfully.");
        return true;
    }

    // Getter to access passenger bookings
    public List<Booking> getBookings() {
        return bookings;
    }

    // Existing getters and setters...
    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
