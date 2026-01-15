public abstract class Booking {

    protected int bookingId;
    protected String bookingDate;

    public Booking(int bookingId, Passenger passenger, String bookingDate) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public abstract String getRouteDescription();
}
