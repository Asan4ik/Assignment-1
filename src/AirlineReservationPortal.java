import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationPortal {

    private static Passenger currentPassenger = null;
    private static final Scanner scanner = new Scanner(System.in);

    // ================= REGISTER =================
    public static void register() {
        scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        String sql =
                "INSERT INTO passengers(first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.executeUpdate();

            System.out.println("Registration successful.");

        } catch (SQLException e) {
            System.out.println("Email already registered.");
        }
    }

    // ================= LOGIN =================
    public static void login() {
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        String sql = "SELECT * FROM passengers WHERE email = ?";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                currentPassenger = new Passenger(
                        rs.getInt("passenger_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );
                System.out.println("Logged in as " + currentPassenger.getFirstName());
            } else {
                System.out.println("Passenger not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= ADD FLIGHT =================
    public static void addFlight() {
        scanner.nextLine();

        System.out.print("Flight number: ");
        String flightNumber = scanner.nextLine();

        System.out.print("Origin: ");
        String origin = scanner.nextLine();

        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();

        String sql =
                "INSERT INTO flights(flight_number, origin, destination, capacity) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, flightNumber);
            ps.setString(2, origin);
            ps.setString(3, destination);
            ps.setInt(4, capacity);
            ps.executeUpdate();

            System.out.println("Flight added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= LOAD FLIGHTS =================
    private static List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();

        String sql = "SELECT * FROM flights";

        try (Connection con = DB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                flights.add(new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flight_number"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    // ================= BOOK FLIGHT =================
    public static void addBooking() {
        if (currentPassenger == null) {
            System.out.println("Login first.");
            return;
        }

        List<Flight> flights = getFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println((i + 1) + ". " +
                    f.getFlightNumber() + " (" +
                    f.getOrigin() + " -> " + f.getDestination() +
                    "), seats: " + f.getCapacity());
        }

        System.out.print("Choose flight: ");
        int index = scanner.nextInt() - 1;
        Flight flight = flights.get(index);

        if (flight.getCapacity() <= 0) {
            System.out.println("No seats available.");
            return;
        }

        scanner.nextLine();
        System.out.print("Booking date: ");
        String date = scanner.nextLine();

        String insertBooking =
                "INSERT INTO bookings(passenger_id, outbound_flight_id, booking_date, booking_type) " +
                        "VALUES (?, ?, ?, 'ONE_WAY')";

        String updateCapacity =
                "UPDATE flights SET capacity = capacity - 1 WHERE flight_id = ?";

        try (Connection con = DB.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement b = con.prepareStatement(insertBooking);
                 PreparedStatement c = con.prepareStatement(updateCapacity)) {

                b.setInt(1, currentPassenger.getPassengerId());
                b.setInt(2, flight.getFlightId());
                b.setString(3, date);
                b.executeUpdate();

                c.setInt(1, flight.getFlightId());
                c.executeUpdate();

                con.commit();
                System.out.println("Booking successful.");

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= SHOW BOOKINGS =================
    public static void showBookings() {
        if (currentPassenger == null) {
            System.out.println("Login first.");
            return;
        }

        String sql =
                "SELECT b.booking_id, b.booking_date, f.origin, f.destination " +
                        "FROM bookings b JOIN flights f " +
                        "ON b.outbound_flight_id = f.flight_id " +
                        "WHERE b.passenger_id = ?";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, currentPassenger.getPassengerId());
            ResultSet rs = ps.executeQuery();

            boolean hasBookings = false;

            System.out.println("\n=== Your Bookings ===");

            while (rs.next()) {
                hasBookings = true;
                System.out.println(
                        "Booking ID: " + rs.getInt("booking_id") +
                                " | Route: " + rs.getString("origin") +
                                " -> " + rs.getString("destination") +
                                " | Date: " + rs.getString("booking_date")
                );
            }

            if (!hasBookings) {
                System.out.println("You have no bookings yet.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ================= MENU =================
    private static void passengerMenu() {
        System.out.println("\n1. Book Flight");
        System.out.println("2. View Bookings");
        System.out.println("3. Add Flight (Admin)");
        System.out.println("4. Logout");

        switch (scanner.nextInt()) {
            case 1 -> addBooking();
            case 2 -> showBookings();
            case 3 -> addFlight();
            case 4 -> currentPassenger = null;
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        while (true) {
            if (currentPassenger == null) {
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                switch (scanner.nextInt()) {
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
