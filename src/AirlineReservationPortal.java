import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationPortal {

    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    private static Passenger currentPassenger = null;

    private static int nextPassengerId = 1;
    private static int nextFlightId = 1;
    private static int nextBookingId = 1;

    private static final Scanner scanner = new Scanner(System.in);

    // ================== REGISTER ==================
    public static void register() {
        scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        for (Passenger p : passengers) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered.");
                return;
            }
        }

        Passenger passenger = new Passenger(
                nextPassengerId++,
                firstName,
                lastName,
                email
        );

        passengers.add(passenger);
        System.out.println("Registration successful. You can now log in.");
    }

    // ================== LOGIN ==================
    public static void login() {
        scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        for (Passenger p : passengers) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                currentPassenger = p;
                System.out.println("Logged in as " + p.getFirstName());
                return;
            }
        }

        System.out.println("Passenger not found. Please register first.");
    }

    // ================== ADD FLIGHT (SYSTEM) ==================
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
        if (currentPassenger == null) {
            System.out.println("Please log in first.");
            return;
        }

        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        System.out.println("\nSelect Flight:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() +
                    " (" + f.getOrigin() + " -> " + f.getDestination() + ")");
        }

        int flightIndex = scanner.nextInt() - 1;
        Flight flight = flights.get(flightIndex);

        scanner.nextLine();
        System.out.print("Enter booking date: ");
        String bookingDate = scanner.nextLine();

        Booking booking = currentPassenger.bookFlight(
                flight,
                nextBookingId++,
                bookingDate
        );

        if (booking != null) {
            bookings.add(booking);
            System.out.println("Booking created successfully.");
        }
    }

    // ================== SHOW MY BOOKINGS ==================
    public static void showBookings() {
        if (currentPassenger == null) {
            System.out.println("Please log in first.");
            return;
        }

        List<Booking> passengerBookings = currentPassenger.getBookings();

        if (passengerBookings.isEmpty()) {
            System.out.println("You have no bookings.");
            return;
        }

        System.out.println("\nYour Bookings:");
        for (Booking booking : passengerBookings) {
            System.out.println(
                    "Booking ID: " + booking.getBookingId() +
                            " | Flight: " + booking.getFlight().getFlightNumber() +
                            " | Route: " + booking.getFlight().getOrigin() +
                            " -> " + booking.getFlight().getDestination() +
                            " | Date: " + booking.getBookingDate()
            );
        }
    }

    // ================== SHOW FLIGHTS ==================
    public static void showFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        System.out.println("\nAvailable Flights:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() + " | " +
                    f.getOrigin() + " -> " +
                    f.getDestination() +
                    " | Capacity: " + f.getCapacity());
        }
    }

    // ================== PASSENGER MENU ==================
    private static void passengerMenu() {
        System.out.println("\nPassenger Menu");
        System.out.println("1. Book Flight");
        System.out.println("2. View My Bookings");
        System.out.println("3. View Flights");
        System.out.println("4. Logout");

        int input = scanner.nextInt();

        switch (input) {
            case 1 -> addBooking();
            case 2 -> showBookings();
            case 3 -> showFlights();
            case 4 -> {
                currentPassenger = null;
                System.out.println("Logged out.");
            }
            default -> System.out.println("Invalid option.");
        }
    }

    // ================== MAIN ==================
    public static void main(String[] args) {
        // Preload flights (optional, for testing)
        // addFlight();

        while (true) {
            if (currentPassenger == null) {
                System.out.println("\nWelcome to Airline Reservation Portal");
                System.out.println("1. Register");
                System.out.println("2. Log in");
                System.out.println("3. Exit");

                int input = scanner.nextInt();

                switch (input) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> {
                        System.out.println("Exiting system.");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } else {
                passengerMenu();
            }
        }
    }
}
