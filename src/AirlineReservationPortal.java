import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationPortal {

    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    private static int nextPassengerId = 1;
    private static int nextFlightId = 1;
    private static int nextBookingId = 1;

    private static final Scanner scanner = new Scanner(System.in);

    // ================== ADD PASSENGER ==================
    public static void addPassenger() {
        scanner.nextLine();

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

        passengers.add(passenger);
        System.out.println("Passenger added successfully.");
    }

    // ================== ADD FLIGHT ==================
    public static void addFlight() {
        scanner.nextLine();

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

        flights.add(flight);
        System.out.println("Flight added successfully.");
    }

    // ================== ADD BOOKING ==================
    public static void addBooking() {
        if (passengers.isEmpty() || flights.isEmpty()) {
            System.out.println("At least one passenger and one flight are required.");
            return;
        }

        System.out.println("\nSelect Passenger:");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.println((i + 1) + ". " + p.getFirstName() + " " + p.getLastName());
        }

        int passengerIndex = scanner.nextInt() - 1;
        Passenger passenger = passengers.get(passengerIndex);

        System.out.println("\nSelect Flight:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " + f.getFlightNumber()
                    + " (" + f.getOrigin() + " -> " + f.getDestination() + ")");
        }

        int flightIndex = scanner.nextInt() - 1;
        Flight flight = flights.get(flightIndex);

        scanner.nextLine();
        System.out.print("Enter booking date: ");
        String bookingDate = scanner.nextLine();

        Booking booking = passenger.bookFlight(
                flight,
                nextBookingId++,
                bookingDate
        );

        if (booking != null) {
            bookings.add(booking);
            System.out.println("Booking created successfully.");
        }
    }

    // ================== SHOW PASSENGERS ==================
    public static void showPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers found.");
            return;
        }

        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.println((i + 1) + ". " +
                    p.getFirstName() + " " +
                    p.getLastName() + " | " +
                    p.getEmail());
        }
    }

    // ================== SHOW BOOKINGS ==================
    public static void showBookings() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers available.");
            return;
        }

        System.out.println("Select Passenger:");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.println((i + 1) + ". " +
                    p.getFirstName() + " " +
                    p.getLastName());
        }

        int input;
        while (true) {
            input = scanner.nextInt();
            if (input >= 1 && input <= passengers.size()) {
                break;
            }
            System.out.println("Invalid option. Try again.");
        }

        Passenger passenger = passengers.get(input - 1);
        List<Booking> passengerBookings = passenger.getBookings();

        if (passengerBookings.isEmpty()) {
            System.out.println("This passenger has no bookings.");
            return;
        }

        System.out.println("\nBookings:");
        for (Booking booking : passengerBookings) {
            System.out.println(booking);
        }
    }

    // ================== SHOW FLIGHTS ==================
    public static void showFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() + " | " +
                    f.getOrigin() + " -> " +
                    f.getDestination() +
                    " | Capacity: " + f.getCapacity());
        }
    }

    // ================== MAIN MENU ==================
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nAirline Reservation Portal");
            System.out.println("1. Add Passenger");
            System.out.println("2. Add Booking");
            System.out.println("3. Add Flight");
            System.out.println("4. Show Passengers");
            System.out.println("5. Show Bookings");
            System.out.println("6. Show Flights");
            System.out.println("7. Exit");

            int input = scanner.nextInt();

            switch (input) {
                case 1 -> addPassenger();
                case 2 -> addBooking();
                case 3 -> addFlight();
                case 4 -> showPassengers();
                case 5 -> showBookings();
                case 6 -> showFlights();
                case 7 -> {
                    System.out.println("Exiting system.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
