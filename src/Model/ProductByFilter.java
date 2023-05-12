package Model;

public class ProductByFilter {
    private int id;
    private String productName;
    private int quantitySold;
    private double totalRevenue;
    private String categoryName;

    public ProductByFilter(int id, String productName, int quantitySold, double totalRevenue, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalRevenue = totalRevenue;
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
