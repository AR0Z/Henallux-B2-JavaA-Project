package Model;

public class Supplier {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String streetAndNumber;
    private int localityFK;

    public Supplier(int id, String name, String phone, String email, String streetAndNumber, int localityFK) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.streetAndNumber = streetAndNumber;
        this.localityFK = localityFK;
    }
}
