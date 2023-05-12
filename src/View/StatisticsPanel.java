package View;

import Controller.ApplicationController;
import Model.Supplier;
import View.ComboBox.ComboBoxCategories;
import View.ComboBox.ComboBoxProducts;
import View.ComboBox.ComboBoxSupplier;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private ComboBoxSupplier comboBoxSupplier;
    private ComboBoxCategories comboBoxCategories;
    private JFormattedTextField startDate, endDate;
    private JComboBox<String> comboBoxOrder;

    private ApplicationController controller;
    private JButton submitButton;
    private JPanel topPanel, centerPanel;
    private JTable table;
    public StatisticsPanel() {
        controller = new ApplicationController();
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3,2,5,5));
        add(topPanel, BorderLayout.NORTH);
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        topPanel.add(new JLabel("Menu de statistique"));
        comboBoxSupplier = new ComboBoxSupplier();
        topPanel.add(comboBoxSupplier);
        comboBoxCategories = new ComboBoxCategories();
        topPanel.add(comboBoxCategories);

        comboBoxOrder = new JComboBox<>(new String[]{"Nom", "Vente", "Chiffre d'affaire", "Category"});
        topPanel.add(comboBoxOrder);


    }
}
