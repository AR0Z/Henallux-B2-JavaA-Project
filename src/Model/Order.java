package Model;

import java.sql.Date;

public class Order {
    private int id;
    private Date orderDate;
    private double isPaid;
    private double reduction;
    private String type;
    private Date invoiceDate;
    private int customerFK;
    private String shippingAddress;
    private String invoiceDetails;

    public Order(int id, Date orderDate, double isPaid, double reduction, String type, Date invoiceDate, int customerFK, String shippingAddress, String invoiceDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.isPaid = isPaid;
        this.reduction = reduction;
        this.type = type;
        this.invoiceDate = invoiceDate;
        this.customerFK = customerFK;
        this.shippingAddress = shippingAddress;
        this.invoiceDetails = invoiceDetails;
    }

}
