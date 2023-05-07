package View;

import Controller.ApplicationController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchProductPanel extends JPanel {

    private JButton submitButton;
    private JPanel topPanel, centerPanel;
    private ArrayList<Product> products;
    private JTable table;
    private String[] columnNames = {"id", "nom", "color", "price", "cost", "size", "stock", "addition_date", "is_shippable", "category_id", "information", "image_link"};
    private ArrayList<Category> categories;
    ApplicationController applicationController;
    public SearchProductPanel() {
        applicationController = new ApplicationController();
        submitButton = new JButton("Listing des produits");
        setLayout(new BorderLayout());
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(submitButton);
        add(topPanel, BorderLayout.NORTH);
        submitButton.addActionListener(l -> {
            products = applicationController.getAllProducts();
            categories = applicationController.getAllCategories();
            Object[][] data = new Object[products.size()][12];
            for (int i = 0; i < products.size(); i++) {
                data[i][0] = products.get(i).getId();
                data[i][1] = products.get(i).getName();
                data[i][2] = products.get(i).getColor();
                data[i][3] = products.get(i).getPrice();
                data[i][4] = products.get(i).getCost();
                data[i][5] = products.get(i).getSize();
                data[i][6] = products.get(i).getStock();
                data[i][7] = products.get(i).getAdditionDate();
                data[i][8] = products.get(i).getShippable();
                data[i][9] = categories.get(products.get(i).getCategory_FK() - 1).getLabel();
                data[i][10] = products.get(i).getDescription();
                data[i][11] = products.get(i).getImgLink();
            }
            table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(700, 70));
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

            JScrollPane scrollPane = new JScrollPane(table);
            centerPanel.add(scrollPane);
            add(centerPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        centerPanel = new JPanel(new FlowLayout());

        add(centerPanel, BorderLayout.CENTER);



    }
}
