package Model;

public class SupplierByCategory {
    private String countryName;
    private String cityName;
    private String supplierName;

    public SupplierByCategory(String countryName, String cityName, String supplierName) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.supplierName = supplierName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
