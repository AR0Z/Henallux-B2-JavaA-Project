package View.ComboBox;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class ComboBoxSupplier extends JComboBox {
    ArrayList<Supplier> suppliers;
    ApplicationController controller;

    public ComboBoxSupplier() {
        controller = new ApplicationController();
        update();
    }

    public void update() {
        if (getItemCount() > 0)
            removeAllItems();
        try {
            suppliers = controller.getAllSuppliers();
            addItem("Choisir un fournisseur");
            for (Supplier supplier : suppliers) {
                addItem(supplier.getName() + " (" + supplier.getId() + ")");
            }
        } catch (DBExceptions e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getId() {
        return suppliers.get(this.getSelectedIndex() - 1).getId();
    }
}
