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
    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }
}
