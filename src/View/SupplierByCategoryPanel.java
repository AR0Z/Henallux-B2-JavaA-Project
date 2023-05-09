package View;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Model.Category;
import Model.SupplierByCategory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SupplierByCategoryPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<SupplierByCategory> suppliersByCategory;
    private ComboBoxCategories comboBoxCategories;
    private JPanel topPanel, centerPanel;
    private String[] columnNames = {"Nom du pays", "Nom de la ville", "Fournisseur"};
    JScrollPane scrollPane;

    public SupplierByCategoryPanel() {
        setLayout(new BorderLayout());

        topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Trouver les fournisseurs qui fournit une catégorie", SwingConstants.CENTER));
        controller = new ApplicationController();
        comboBoxCategories = new ComboBoxCategories();
        comboBoxCategories.addActionListener(l -> {
            if (comboBoxCategories.getSelectedIndex() >= 1) {
                try {
                    Category category = controller.getCategoryById(comboBoxCategories.getId());
                    updateTable(category);
                } catch (DBExceptions e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
            ;
        });

        topPanel.add(comboBoxCategories);
        add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new FlowLayout());
        add(centerPanel, BorderLayout.CENTER);

        scrollPane = new JScrollPane();
        centerPanel.add(scrollPane);
    }

    public void updateComboBox() {
        comboBoxCategories.update();
    }

    public void updateTable(Category category) {

        try {
            suppliersByCategory = controller.getSuppliersByCategory(category.getId());

            Object[][] data = new Object[suppliersByCategory.size()][3];
            for (int i = 0; i < suppliersByCategory.size(); i++) {
                data[i][0] = suppliersByCategory.get(i).getCountryName();
                data[i][1] = suppliersByCategory.get(i).getCityName();
                data[i][2] = suppliersByCategory.get(i).getSupplierName();
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
