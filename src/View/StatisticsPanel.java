package View;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Model.Category;
import Model.Filter;
import Model.Supplier;
import View.ComboBox.ComboBoxCategories;
import View.ComboBox.ComboBoxSupplier;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StatisticsPanel extends JPanel {

    private ComboBoxSupplier comboBoxSupplier;
    private ComboBoxCategories comboBoxCategories;
    private JFormattedTextField startDate, endDate;
    private JComboBox<String> comboBoxOrder;

    private ApplicationController controller;
    private JButton submitButton;
    private JPanel topPanel, centerPanel;
    private JTable table;
    private SimpleDateFormat dateFormat;

    public StatisticsPanel() {
        controller = new ApplicationController();
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0,2,5,5));
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
        topPanel.add(new JLabel("Date de début"));

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        startDate = new JFormattedTextField(dateFormat);
        topPanel.add(startDate);
        topPanel.add(new JLabel("Date de fin"));
        endDate = new JFormattedTextField(dateFormat);
        topPanel.add(endDate);
        submitButton = new JButton("Valider");
        topPanel.add(submitButton);
        add(centerPanel, BorderLayout.CENTER);

    }


}
