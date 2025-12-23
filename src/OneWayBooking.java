public class OneWayBooking extends Booking {

    private final Flight flight;

    public OneWayBooking(int bookingId, Flight flight, Passenger passenger, String bookingDate) {
        super(bookingId, passenger, bookingDate);
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String getRouteDescription() {
        return flight.getOrigin() + " -> " + flight.getDestination();
    }
}
