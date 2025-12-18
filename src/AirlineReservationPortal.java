import java.util.Scanner;

public class AirlineReservationPortal {

    // ============================
    //     STORAGE (ARRAYS)
    // ============================
    private static Passenger[] passengers = new Passenger[100];
    private static Flight[] flights = new Flight[100];
    private static Booking[] bookings = new Booking[100];

    private static int passengerCount = 0;
    private static int flightCount = 0;
    private static int bookingCount = 0;

    private static int nextPassengerId = 1;
    private static int nextFlightId = 1;
    private static int nextBookingId = 1;

    private static final Scanner scanner = new Scanner(System.in);

    // ============================
    //     ADD PASSENGER
    // ============================
    public static void addPassenger() {
        if (passengerCount >= passengers.length) {
            System.out.println("Passenger storage is full.");
            return;
        }

        scanner.nextLine(); // clear buffer

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Passenger passenger = new Passenger(
                nextPassengerId++,
                firstName,
                lastName,
                email
        );

        passengers[passengerCount++] = passenger;
        System.out.println("Passenger added successfully.");
    }

    // ============================
    //     ADD FLIGHT
    // ============================
    public static void addFlight() {
        if (flightCount >= flights.length) {
            System.out.println("Flight storage is full.");
            return;
        }

        scanner.nextLine(); // clear buffer

        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();

        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter departure time: ");
        String departureTime = scanner.nextLine();

        System.out.print("Enter arrival time: ");
        String arrivalTime = scanner.nextLine();

        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        Flight flight = new Flight(
                nextFlightId++,
                flightNumber,
                origin,
                destination,
                departureTime,
                arrivalTime,
                capacity
        );

        flights[flightCount++] = flight;
        System.out.println("Flight added successfully.");
    }

    // ============================
    //     ADD BOOKING
    // ============================
    public static void addBooking() {
        if (passengerCount == 0 || flightCount == 0) {
            System.out.println("At least one passenger and one flight are required.");
            return;
        }

        // Select passenger
        System.out.println("\nSelect Passenger:");
        for (int i = 0; i < passengerCount; i++) {
            Passenger p = passengers[i];
            System.out.println(i + ": " + p.getFirstName() + " " + p.getLastName());
        }

        int passengerIndex = scanner.nextInt();
        Passenger passenger = passengers[passengerIndex];

        // Select flight
        System.out.println("\nSelect Flight:");
        for (int i = 0; i < flightCount; i++) {
            Flight f = flights[i];
            System.out.println(i + ": " + f.getFlightNumber()
                    + " (" + f.getOrigin() + " -> " + f.getDestination() + ")");
        }

        int flightIndex = scanner.nextInt();
        Flight flight = flights[flightIndex];

        scanner.nextLine(); // clear buffer
        System.out.print("Enter booking date: ");
        String bookingDate = scanner.nextLine();

        Booking booking = passenger.bookFlight(
                flight,
                nextBookingId,
                bookingDate
        );

        if (booking != null) {
            bookings[bookingCount++] = booking;
            nextBookingId++;
        }
    }

    // ============================
    //     MAIN MENU
    // ============================
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nAirline Reservation Portal");
            System.out.println("1. Add Passenger");
            System.out.println("2. Add Booking");
            System.out.println("3. Add Flight");
            System.out.println("4. Exit");

            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    addPassenger();
                    break;
                case 2:
                    addBooking();
                    break;
                case 3:
                    addFlight();
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
