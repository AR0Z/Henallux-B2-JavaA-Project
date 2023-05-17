package View.ComboBox;

import Controller.ApplicationController;
import Exceptions.ConnectionException;
import Exceptions.GetAllProductsException;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class ComboBoxProducts extends JComboBox {
    ArrayList<Product> products;
    ApplicationController controller;

    public ComboBoxProducts() {
        try {
            controller = new ApplicationController();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        update();
    }

    public void update() {
        if (this.getItemCount() > 0)
            this.removeAllItems();
        try {
            products = controller.getAllProducts();
            this.addItem("Choisir un produit");
            for (Product product : products) {
                this.addItem(product.getName() + " (" + product.getId() + ")");
            }
        } catch (GetAllProductsException e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getId() {
        return products.get(this.getSelectedIndex() - 1).getId();
    }
}
