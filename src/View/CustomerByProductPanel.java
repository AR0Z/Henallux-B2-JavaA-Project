package View;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Model.CustomerByProduct;
import Model.Product;
import View.ComboBox.ComboBoxProducts;
import View.TableModels.CustomersByProductModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomerByProductPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<CustomerByProduct> customerByProducts;
    private ComboBoxProducts comboBoxProducts;
    private JPanel topPanel, centerPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private CustomersByProductModel model;

    public CustomerByProductPanel() {
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

    public void updateTable(Product product) {

        try {
            customerByProducts = controller.getCustomersWhoPurchasedProduct(product.getId());

            if(table == null){
                model = new CustomersByProductModel(customerByProducts);
                table = new JTable(model);
                table.setPreferredScrollableViewportSize(new Dimension(700, 300));
                table.setFillsViewportHeight(true);

                scrollPane.setViewportView(table);
                revalidate();
                repaint();
            }else{
                model.update(customerByProducts);
            }

        } catch (DBExceptions e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
