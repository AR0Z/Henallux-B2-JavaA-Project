package Model;

import java.time.LocalDate;

public class CustomerByProduct {
    private int customerID;
    private String customerFirstName;
    private String customerLastName;
    private int Quantity;
    private LocalDate dateOrder;

    public CustomerByProduct(int customerID, String customerFirstName, String customerLastName, int Quantity, LocalDate dateOrder) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.Quantity = Quantity;
        this.dateOrder = dateOrder;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }
}
