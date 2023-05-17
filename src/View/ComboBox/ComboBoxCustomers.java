package View.ComboBox;

import Controller.ApplicationController;
import Exceptions.GetAllCustomersException;
import Model.Customer;

import javax.swing.*;
import java.util.ArrayList;

public class ComboBoxCustomers extends JComboBox {
    ArrayList<Customer> customers;
    ApplicationController controller;

    public ComboBoxCustomers() {
        controller = new ApplicationController();
        update();
    }

    public void update() {
        if (this.getItemCount() > 0)
            this.removeAllItems();
        try {
            customers = controller.getAllCustomers();
            this.addItem("Choisir un client");
            for (Customer customer : customers) {
                this.addItem(customer.getName() + " (" + customer.getId() + ")");
            }
        } catch (GetAllCustomersException e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getId() {
        return customers.get(this.getSelectedIndex() - 1).getId();
    }
}
