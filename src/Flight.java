public class Flight {

    private final int flightId;
    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final int capacity;

    public Flight(int flightId, String flightNumber,
                  String origin, String destination,
                  int capacity) {

        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }
}
