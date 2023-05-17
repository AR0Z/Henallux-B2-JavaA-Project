package View;

import Controller.ApplicationController;
import Exceptions.ConnectionException;
import Exceptions.GetAllCategoriesException;
import Exceptions.GetAllProductsException;
import Model.Category;
import Model.Product;
import View.TableModels.AllProductsModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListingProductPanel extends JPanel {

    private JButton submitButton;
    private JPanel topPanel, centerPanel;
    private ArrayList<Product> products;
    private String[] columnNames = {"id", "nom", "color", "price", "cost", "size", "stock", "addition_date", "is_shippable", "category_id", "information", "image_link"};
    private ArrayList<Category> categories;
    ApplicationController controller;
    JScrollPane scrollPane;

    public ListingProductPanel() {
        try {
            controller = new ApplicationController();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        submitButton = new JButton("Afficher les produits");
        setLayout(new BorderLayout());
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(submitButton);
        add(topPanel, BorderLayout.NORTH);
        submitButton.addActionListener(l -> submit());
        centerPanel = new JPanel(new FlowLayout());
        scrollPane = new JScrollPane();
        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void submit() {
        try {
            products = controller.getAllProducts();
            categories = controller.getAllCategories();
            products.forEach(product -> {
                product.setCategory(categories.get(product.getCategory_FK() - 1));
            });

            JTable table = new JTable(new AllProductsModel(products));
            table.setPreferredScrollableViewportSize(new Dimension(700, 300));
            table.setFillsViewportHeight(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setPreferredWidth(70);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setPreferredWidth(35);
            table.getColumnModel().getColumn(4).setPreferredWidth(35);
            table.getColumnModel().getColumn(5).setPreferredWidth(35);
            table.getColumnModel().getColumn(6).setPreferredWidth(35);
            table.getColumnModel().getColumn(7).setPreferredWidth(75);
            table.getColumnModel().getColumn(8).setPreferredWidth(20);
            table.getColumnModel().getColumn(9).setPreferredWidth(50);
            table.getColumnModel().getColumn(10).setPreferredWidth(100);
            table.getColumnModel().getColumn(11).setPreferredWidth(100);

            scrollPane.setViewportView(table);
            revalidate();
            repaint();
        } catch (GetAllProductsException | GetAllCategoriesException e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
