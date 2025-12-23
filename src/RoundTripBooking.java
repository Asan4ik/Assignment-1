public class RoundTripBooking extends Booking {

    private final Flight outboundFlight;
    private final Flight returnFlight;

    public RoundTripBooking(
            int bookingId,
            Flight outboundFlight,
            Flight returnFlight,
            Passenger passenger,
            String bookingDate) {

        super(bookingId, passenger, bookingDate);
        this.outboundFlight = outboundFlight;
        this.returnFlight = returnFlight;
    }

    @Override
    public String getRouteDescription() {
        return outboundFlight.getOrigin() +
                " -> " + outboundFlight.getDestination() +
                " -> " + returnFlight.getDestination();
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                " | Outbound: " + outboundFlight.getFlightNumber() +
                " | Return: " + returnFlight.getFlightNumber() +
                " | Route: " + getRouteDescription() +
                " | Date: " + bookingDate;
    }
}
