public class Passenger {

    private final int passengerId;
    private final String firstName;
    private final String lastName;
    private final String email;

    public Passenger(int passengerId, String firstName,
                     String lastName, String email) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
