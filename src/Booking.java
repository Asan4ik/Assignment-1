public class Booking {

    private int bookingId;
    private Flight flight;
    private Passenger passenger;
    private String bookingDate; // Could use LocalDate

    public Booking() {
    }

    public Booking(int bookingId, Flight flight, Passenger passenger, String bookingDate) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
