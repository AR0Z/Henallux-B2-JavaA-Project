package View;

import Controller.ApplicationController;
import Exceptions.ConnectionException;
import Exceptions.GetAllCategoriesException;
import Exceptions.GetAllSuppliersException;
import Exceptions.GetProductsByFilterException;
import Model.Category;
import Model.Filter;
import Model.Supplier;
import View.ComboBox.ComboBoxCategories;
import View.ComboBox.ComboBoxSupplier;
import View.TableModels.StatisticsModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
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
    private JCheckBox checkBox;
    private SimpleDateFormat dateFormat;

    public StatisticsPanel() {
        try {
            controller = new ApplicationController();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 2, 5, 5));
        add(topPanel, BorderLayout.NORTH);
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        topPanel.add(new JLabel("Menu de statistique"));
        comboBoxSupplier = new ComboBoxSupplier();
        topPanel.add(comboBoxSupplier);
        comboBoxCategories = new ComboBoxCategories();
        topPanel.add(comboBoxCategories);

        comboBoxOrder = new JComboBox<>(new String[]{"Trié par (Nom par défaut)", "Vente", "Chiffre d'affaire", "Categorie"});
        topPanel.add(comboBoxOrder);
        topPanel.add(new JLabel("Trie par date (optionnel)"));
        checkBox = new JCheckBox();
        topPanel.add(checkBox);
        checkBox.addActionListener(l -> {
            if (checkBox.isSelected()) {
                startDate.setEditable(true);
                endDate.setEditable(true);
            } else {
                startDate.setEditable(false);
                endDate.setEditable(false);
                startDate.setText("");
                endDate.setText("");
            }
        });

        topPanel.add(new JLabel("Date de début"));

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        startDate = new JFormattedTextField(dateFormat);
        startDate.setEditable(false);
        topPanel.add(startDate);
        topPanel.add(new JLabel("Date de fin"));
        endDate = new JFormattedTextField(dateFormat);
        endDate.setEditable(false);
        topPanel.add(endDate);
        submitButton = new JButton("Valider");
        topPanel.add(submitButton);
        add(centerPanel, BorderLayout.CENTER);

        submitButton.addActionListener(l -> sumbit());
    }

    public void sumbit() {
        Boolean valide = true;
        if (checkBox.isSelected()) {
            if (!checkFields()) {
                valide = false;
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (!validateDate()) {
                valide = false;
            }
        }
        if (valide) {
            try {
                Category category = null;
                if (comboBoxCategories.getSelectedIndex() > 0) {
                    ArrayList<Category> categories = controller.getAllCategories();
                    category = categories.get(comboBoxCategories.getSelectedIndex() - 1);
                }
                Supplier supplier = null;
                if (comboBoxSupplier.getSelectedIndex() > 0) {
                    ArrayList<Supplier> suppliers = controller.getAllSuppliers();
                    supplier = suppliers.get(comboBoxSupplier.getSelectedIndex() - 1);
                }
                if (!checkBox.isSelected()) {
                    startDate.setText("");
                    endDate.setText("");
                }
                Filter filter = new Filter(category, supplier, startDate.getText(), endDate.getText(), comboBoxOrder.getSelectedIndex());
                table = new JTable(new StatisticsModel(controller.getProductsByFilter(filter)));
                centerPanel.removeAll();
                centerPanel.add(new JScrollPane(table));
                centerPanel.revalidate();
                centerPanel.repaint();
            } catch (GetAllCategoriesException | GetAllSuppliersException | GetProductsByFilterException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Boolean checkFields() {
        Boolean check = true;
        if (startDate.getText().isBlank()) {
            check = false;
            startDate.setBackground(new Color(237, 123, 133, 255));
        } else {
            startDate.setBackground(Color.WHITE);
        }

        if (endDate.getText().isBlank()) {
            check = false;
            endDate.setBackground(new Color(237, 123, 133, 255));
        } else {
            endDate.setBackground(Color.WHITE);
        }
        return check;
    }

    private Boolean validateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Boolean check = true;
        try {
            if (dateFormat.parse(startDate.getText()).getYear() + 1900 < 2000) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une date de début valide (jj/mm/aaaa) postérieur a 2000", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (dateFormat.parse(endDate.getText()).getYear() + 1900 < 2000)
                JOptionPane.showMessageDialog(null, "Veuillez entrer une date de fin valide (jj/mm/aaaa) postérieur a 2000", "Erreur", JOptionPane.ERROR_MESSAGE);
            else if (dateFormat.parse(startDate.getText()).after(dateFormat.parse(endDate.getText())))
                JOptionPane.showMessageDialog(null, "Veuillez entrer une date de début valide (jj/mm/aaaa) antérieur a la date de fin", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            check = false;
        }
        return check;
    }


}



