package View;

import Controller.ApplicationController;
import Exceptions.GetAllSuppliersException;
import Exceptions.GetCategoryByIdException;
import Exceptions.GetCustomerByIdException;
import Exceptions.GetSuppliersByCategoryException;
import Model.Category;
import Model.SupplierByCategory;
import View.ComboBox.ComboBoxCategories;
import View.TableModels.SuppliersByCategoryModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SupplierByCategoryPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<SupplierByCategory> suppliersByCategory;
    private ComboBoxCategories comboBoxCategories;
    private JPanel topPanel, centerPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private SuppliersByCategoryModel model;

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
                } catch (GetCategoryByIdException e) {
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

    public void updateTable(Category category) {

        try {
            suppliersByCategory = controller.getSuppliersByCategory(category.getId());

            if (table == null){
                model = new SuppliersByCategoryModel(suppliersByCategory);
                table = new JTable(model);
                table.setPreferredScrollableViewportSize(new Dimension(700, 300));
                table.setFillsViewportHeight(true);

                scrollPane.setViewportView(table);
                revalidate();
                repaint();
            } else {
                model.update(suppliersByCategory);
            }

        } catch (GetSuppliersByCategoryException e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
