package Model;

public class Supplier {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String StreetAndNumber;
    private int localityFK;

    public Supplier(int id, String name, String email, String phone, String streetAndNumber, int localityFK) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        StreetAndNumber = streetAndNumber;
        this.localityFK = localityFK;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
