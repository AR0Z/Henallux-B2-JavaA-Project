package Model;

import java.util.ArrayList;

public class ProductByFilter {
    private int id;
    private String productName;

    private String categoryName;
    private ArrayList<Line> lines;

    public ProductByFilter(int id, String productName, String categoryName,ArrayList<Line> lines) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.lines = lines;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantitySold() {
        int quantity = 0;
        for (Line line : lines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Line line : lines) {
            total += line.getPrice() * line.getQuantity();
        }
        return total;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
