package View;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Model.CustomerByProduct;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchCustomerWhoPurchasedProductPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<CustomerByProduct> customerByProducts;
    private ComboBoxProducts comboBoxProducts;
    private JPanel topPanel, centerPanel;
    private String[] columnNames = {"ID du client", "Prénom du client", "Nom du client", "Quantité", "Date d'achat"};
    JScrollPane scrollPane;

    public SearchCustomerWhoPurchasedProductPanel() {
        setLayout(new BorderLayout());

        topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Menu de recherche qui a acheté cet article", SwingConstants.CENTER));
        controller = new ApplicationController();
        comboBoxProducts = new ComboBoxProducts();
        comboBoxProducts.addActionListener(l -> {
            if (comboBoxProducts.getSelectedIndex() >= 1) {
                try {
                    Product product = controller.getProductById(comboBoxProducts.getId());
                    updateTable(product);
                } catch (DBExceptions e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
            ;
        });

        topPanel.add(comboBoxProducts);
        add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new FlowLayout());
        add(centerPanel, BorderLayout.CENTER);

        scrollPane = new JScrollPane();
        centerPanel.add(scrollPane);
    }

    public void updateComboBox() {
        comboBoxProducts.update();
    }

    public void updateTable(Product product) {

        try {
            customerByProducts = controller.getCustomersWhoPurchasedProduct(product.getId());

            Object[][] data = new Object[customerByProducts.size()][5];
            for (int i = 0; i < customerByProducts.size(); i++) {
                data[i][0] = customerByProducts.get(i).getCustomerID();
                data[i][1] = customerByProducts.get(i).getCustomerFirstName();
                data[i][2] = customerByProducts.get(i).getCustomerLastName();
                data[i][3] = customerByProducts.get(i).getQuantity();
                data[i][4] = customerByProducts.get(i).getDateOrder();
            }

            JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(700, 300));
            table.setFillsViewportHeight(true);

            scrollPane.setViewportView(table);

            centerPanel.add(scrollPane);
            add(centerPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        } catch (DBExceptions e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
