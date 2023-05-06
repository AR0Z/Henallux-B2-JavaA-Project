package Model;

public class Locality {
    private int id;
    private String label;
    private int postalCode;
    private int countryFK;

    public Locality(int id, String label, int postalCode, int countryFK) {
        this.id = id;
        this.label = label;
        this.postalCode = postalCode;
        this.countryFK = countryFK;
    }
}
