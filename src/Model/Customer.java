package Model;

public class Customer {

    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private int localityFK;
    private String phone;
    private String streetAndNumber;

    public Customer(int id, String lastName, String firstName, String email, int localityFK, String phone, String streetAndNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.localityFK = localityFK;
        this.phone = phone;
        this.streetAndNumber = streetAndNumber;
    }

    public Customer(int id, String lastName, String firstName, String email, int localityFK, String streetAndNumber) {
        this(id, lastName, firstName, email, localityFK, null, streetAndNumber);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return lastName + " " + firstName;
    }
}
