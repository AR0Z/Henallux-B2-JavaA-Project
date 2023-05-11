package View;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Model.Product;
import View.ComboBox.ComboBoxProducts;

import javax.swing.*;
import java.awt.*;

public class RemoveProductPanel extends JPanel {

    private ApplicationController controller;
    private ComboBoxProducts comboBoxProducts;
    private JButton submitButton;
    private JPanel topPanel, bottomPanel;
    private Product product;

    public RemoveProductPanel() {
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Menu de suppression de produit", SwingConstants.CENTER));
        controller = new ApplicationController();
        comboBoxProducts = new ComboBoxProducts();
        comboBoxProducts.addActionListener(l -> {
            if (comboBoxProducts.getSelectedIndex() >= 1) {
                try {
                    product = controller.getProductById(comboBoxProducts.getId());
                } catch (DBExceptions e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            ;
        });
        topPanel.add(comboBoxProducts);

        bottomPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Supprimer");
        submitButton.addActionListener(l -> submit());
        bottomPanel.add(submitButton);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void submit() {
        if (comboBoxProducts.getSelectedIndex() >= 1) {
            int response = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce produit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                if (product != null) {
                    try {
                        controller.deleteProduct(product.getId());
                        JOptionPane.showMessageDialog(this, "Le produit a bien été supprimé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        comboBoxProducts.update();
                    } catch (DBExceptions e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un produit !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


}
