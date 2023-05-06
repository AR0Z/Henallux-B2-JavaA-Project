package Model;

public class Supply {
    private double cost;
    private int productFK;
    private int supplierFK;

    public Supply(double cost, int productFK, int supplierFK) {
        this.cost = cost;
        this.productFK = productFK;
        this.supplierFK = supplierFK;
    }
}
