package Model;

public class Line {
    private int quantity;
    private int productFK;
    private int orderFK;
    private double unitaryPrice;

    public Line(int quantity, int productFK, int orderFK, double unitaryPrice) {
        this.quantity = quantity;
        this.productFK = productFK;
        this.orderFK = orderFK;
        this.unitaryPrice = unitaryPrice;
    }


}
