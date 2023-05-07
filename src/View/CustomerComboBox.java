package View;

import Controller.ApplicationController;
import Model.Customer;

import javax.swing.*;
import java.util.ArrayList;

public class CustomerComboBox  extends JComboBox {
    ArrayList<Customer> customers;
    ApplicationController controller;

    public CustomerComboBox() {
        controller = new ApplicationController();
        update();
    }

    public void update(){
        if (this.getItemCount() > 0)
            this.removeAllItems();
        customers = controller.getAllCustomers();
        this.addItem("Choisir un client");
        for (Customer customer : customers) {
            this.addItem(customer.getName() + " (" + customer.getId() + ")");
        }
    }
}
