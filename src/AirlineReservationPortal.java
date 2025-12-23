import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationPortal {

    private static final List<Passenger> passengers = new ArrayList<>();
    private static final List<Flight> flights = new ArrayList<>();

    private static Passenger currentPassenger = null;

    private static int nextPassengerId = 1;
    private static int nextFlightId = 1;
    private static int nextBookingId = 1;

    private static final Scanner scanner = new Scanner(System.in);

    // ================== REGISTER ==================
    public static void register() {
        scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        for (Passenger p : passengers) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered.");
                return;
            }
        }

        Passenger passenger =
                new Passenger(nextPassengerId++, firstName, lastName, email);

        passengers.add(passenger);
        System.out.println("Registration successful.");
    }

    // ================== LOGIN ==================
    public static void login() {
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        for (Passenger p : passengers) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                currentPassenger = p;
                System.out.println("Logged in as " + p.getFirstName());
                return;
            }
        }

        System.out.println("Passenger not found.");
    }

    // ================== ADD FLIGHT ==================
    public static void addFlight() {
        scanner.nextLine();

        System.out.print("Flight number: ");
        String flightNumber = scanner.nextLine();

        System.out.print("Origin: ");
        String origin = scanner.nextLine();

        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Departure time: ");
        String dep = scanner.nextLine();

        System.out.print("Arrival time: ");
        String arr = scanner.nextLine();

        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();

        flights.add(new Flight(
                nextFlightId++, flightNumber, origin,
                destination, dep, arr, capacity
        ));

        System.out.println("Flight added.");
    }

    // ================== BOOK FLIGHT ==================
    public static void addBooking() {
        if (currentPassenger == null) {
            System.out.println("Login first.");
            return;
        }

        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        System.out.println("Booking type:");
        System.out.println("1. One-way");
        System.out.println("2. Round-trip");

        int type = scanner.nextInt();

        if (type == 1) {
            createOneWayBooking();
        } else if (type == 2) {
            createRoundTripBooking();
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void createOneWayBooking() {
        System.out.println("Select flight:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() + " (" +
                    f.getOrigin() + " -> " +
                    f.getDestination() + ")");
        }

        int index = scanner.nextInt() - 1;
        Flight flight = flights.get(index);

        if (flight.getCapacity() <= 0) {
            System.out.println("No seats available.");
            return;
        }

        scanner.nextLine();
        System.out.print("Booking date: ");
        String bookingDate = scanner.nextLine();

        flight.setCapacity(flight.getCapacity() - 1);

        Booking booking = new OneWayBooking(
                nextBookingId++, flight, currentPassenger, bookingDate
        );

        currentPassenger.addBooking(booking);
        System.out.println("One-way booking successful.");
    }

    private static void createRoundTripBooking() {
        System.out.println("Select outbound flight:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() + " (" +
                    f.getOrigin() + " -> " +
                    f.getDestination() + ")");
        }

        int outIndex = scanner.nextInt() - 1;
        Flight outbound = flights.get(outIndex);

        System.out.println("Select return flight:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() + " (" +
                    f.getOrigin() + " -> " +
                    f.getDestination() + ")");
        }

        int retIndex = scanner.nextInt() - 1;
        Flight ret = flights.get(retIndex);

        if (outbound.getCapacity() <= 0 || ret.getCapacity() <= 0) {
            System.out.println("No seats available on one of the flights.");
            return;
        }

        scanner.nextLine();
        System.out.print("Booking date: ");
        String bookingDate = scanner.nextLine();

        outbound.setCapacity(outbound.getCapacity() - 1);
        ret.setCapacity(ret.getCapacity() - 1);

        Booking booking = new RoundTripBooking(
                nextBookingId++, outbound, ret, currentPassenger, bookingDate
        );

        currentPassenger.addBooking(booking);
        System.out.println("Round-trip booking successful.");
    }


    // ================== SHOW BOOKINGS ==================
    public static void showBookings() {
        if (currentPassenger == null) {
            System.out.println("Login first.");
            return;
        }

        if (currentPassenger.getBookings().isEmpty()) {
            System.out.println("No bookings.");
            return;
        }

        for (Booking b : currentPassenger.getBookings()) {
            System.out.println(
                    "Booking ID: " + b.getBookingId() +
                            " | Route: " + b.getRouteDescription() +
                            " | Date: " + b.getBookingDate()
            );
        }
    }

    // ================== MENU ==================
    private static void passengerMenu() {
        System.out.println("\n1. Book Flight");
        System.out.println("2. View Bookings");
        System.out.println("3. Add Flight (Admin)");
        System.out.println("4. Logout");

        int input = scanner.nextInt();

        switch (input) {
            case 1 -> addBooking();
            case 2 -> showBookings();
            case 3 -> addFlight();
            case 4 -> currentPassenger = null;
        }
    }

    // ================== MAIN ==================
    public static void main(String[] args) {

        while (true) {
            if (currentPassenger == null) {
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                int input = scanner.nextInt();

                switch (input) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> System.exit(0);
                }
            } else {
                passengerMenu();
            }
        }
    }
}
