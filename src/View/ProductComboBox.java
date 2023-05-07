package View;

import Controller.ApplicationController;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class ProductComboBox extends JComboBox {
    ArrayList<Product> products;
    ApplicationController controller;
    public ProductComboBox() {
        controller = new ApplicationController();
        update();
    }

    public void update() {
        if (this.getItemCount() > 0)
            this.removeAllItems();
        products = controller.getAllProducts();
        this.addItem("Choisir un produit");
        for (Product product : products) {
            this.addItem(product.getName() + " (" + product.getId() + ")");
        }
    }
}
