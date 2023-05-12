package View;

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
    public StatisticsPanel() {
        setLayout(new GridLayout(3,2,5,5));
        add(new JLabel("Menu de statistique"));
        comboBoxSupplier = new ComboBoxSupplier();
        add(comboBoxSupplier);
        comboBoxCategories = new ComboBoxCategories();
        add(comboBoxCategories);

        comboBoxOrder = new JComboBox<>(new String[]{"Nom", "Vente", "Chiffre d'affaire", "Category"});
        add(comboBoxOrder);
    }
}
