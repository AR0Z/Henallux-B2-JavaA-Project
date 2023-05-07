package View;

import Controller.ApplicationController;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RemoveProductPanel extends JPanel {

    private ApplicationController applicationController;
    private ArrayList<Product> products;
    private ProductComboBox productComboBox;
    private Product product;
    private JButton submitButton;
    private JPanel topPanel, bottomPanel;
    public RemoveProductPanel() {
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Menu de suppression de produit", SwingConstants.CENTER));
        applicationController = new ApplicationController();
        products = applicationController.getAllProducts();
        productComboBox = new ProductComboBox();
        productComboBox.addActionListener(l -> {
            if (productComboBox.getSelectedIndex() >= 1) {
                product = products.get(productComboBox.getSelectedIndex() - 1);
            };
        });
        topPanel.add(productComboBox);

        bottomPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Supprimer");
        submitButton.addActionListener(l -> submit());
        bottomPanel.add(submitButton);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void submit() {
        if (productComboBox.getSelectedIndex() >= 1) {
            int response = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce produit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                if (product != null) {
                    applicationController.deleteProduct(product);
                    JOptionPane.showMessageDialog(this, "Le produit a bien été supprimé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    productComboBox.update();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un produit !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


}
