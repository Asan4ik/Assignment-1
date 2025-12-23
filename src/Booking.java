public abstract class Booking {

    protected int bookingId;
    protected Passenger passenger;
    protected String bookingDate;

    public Booking(int bookingId, Passenger passenger, String bookingDate) {
        this.bookingId = bookingId;
        this.passenger = passenger;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public abstract String getRouteDescription();
}
