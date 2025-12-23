import java.util.ArrayList;
import java.util.List;

public class Passenger {

    private final int passengerId;
    private final String firstName;
    private final String email;

    private final List<Booking> bookings = new ArrayList<>();

    public Passenger(int passengerId, String firstName, String lastName, String email) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.email = email;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
