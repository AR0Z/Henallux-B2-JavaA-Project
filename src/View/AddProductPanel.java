package View;

import Controller.ApplicationController;
import Exceptions.AddProductException;
import Exceptions.GetCategoryByIdException;
import Model.Category;
import Model.Product;
import View.ComboBox.ComboBoxCategories;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class AddProductPanel extends JPanel {
    private JLabel nameLabel, colorLabel, priceLabel, costLabel, sizeLabel, stockLabel, shippableLabel, descriptionLabel, imgLinkLabel, categoryLabel, dateLabel;
    private JTextField nameField, priceField, costField, sizeField, stockField, imgLinkField;
    private JFormattedTextField dateField;
    private JTextArea descriptionTextArea;
    private JComboBox<String> colorComboBox;
    private ComboBoxCategories categoryComboBox;
    private JCheckBox shippableCheckBox;
    private ApplicationController controller;
    private JButton submitButton, clearButton;
    private JScrollPane scrollPane;

    public AddProductPanel() {
        setLayout(new GridLayout(14, 2, 5, 5));
        add(new JLabel("Menu d'ajout de produit", SwingConstants.CENTER));
        add(new JLabel("Les champs marqués d'une * sont obligatoires", SwingConstants.CENTER));

        controller = new ApplicationController();

        nameLabel = new JLabel("*Nom:", SwingConstants.RIGHT);
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        colorLabel = new JLabel("*Couleur:", SwingConstants.RIGHT);
        colorComboBox = new JComboBox<>(new String[]{"Rouge", "Bleu", "Vert", "Jaune", "Noir", "Blanc", "Rose", "Gris", "Orange", "Marron"});
        add(colorLabel);
        add(colorComboBox);

        categoryLabel = new JLabel("*Categorie:", SwingConstants.RIGHT);
        categoryComboBox = new ComboBoxCategories();

        add(categoryLabel);
        add(categoryComboBox);

        priceLabel = new JLabel("*Prix:", SwingConstants.RIGHT);
        priceField = new JTextField();
        add(priceLabel);
        add(priceField);

        costLabel = new JLabel("*Cout:", SwingConstants.RIGHT);
        costField = new JTextField();
        add(costLabel);
        add(costField);

        sizeLabel = new JLabel("*Taille (m³):", SwingConstants.RIGHT);
        sizeField = new JTextField();
        add(sizeLabel);
        add(sizeField);

        stockLabel = new JLabel("*Stock:", SwingConstants.RIGHT);
        stockField = new JTextField();
        add(stockLabel);
        add(stockField);


        shippableLabel = new JLabel("*Est envoyable:", SwingConstants.RIGHT);
        shippableCheckBox = new JCheckBox();
        add(shippableLabel);
        add(shippableCheckBox);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateLabel = new JLabel("Date d'ajout (aujourd'hui si vide):", SwingConstants.RIGHT);
        dateField = new JFormattedTextField( dateFormat );
        add(dateLabel);
        add(dateField);


        descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setLineWrap(true);
        scrollPane = new JScrollPane(descriptionTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(descriptionLabel);
        add(scrollPane);

        imgLinkLabel = new JLabel("Lien d'image:", SwingConstants.RIGHT);
        imgLinkField = new JTextField();
        add(imgLinkLabel);
        add(imgLinkField);


        submitButton = new JButton("Ajouter");
        submitButton.addActionListener(e -> submit());
        add(submitButton);

        clearButton = new JButton("Effacer");
        clearButton.addActionListener(e -> clear());
        add(clearButton);
        setVisible(true);
    }

    private void submit() {
        String fieldName = "";
        try {
            if (!checkFields()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                fieldName = "Prix";
                double price = Double.parseDouble(priceField.getText());

                fieldName = "Coût";
                double cost = Double.parseDouble(costField.getText());

                fieldName = "Taille";
                double size = Double.parseDouble(sizeField.getText());

                fieldName = "Stock";
                int stock = Integer.parseInt(stockField.getText());

                if (!isNameValid(nameField.getText())) {
                    JOptionPane.showMessageDialog(null, "Le nom doit contenir entre 3 et 50 caractères (lettres et chiffres uniquement)", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (!isDoubleValid(price)) {
                    JOptionPane.showMessageDialog(null, "Le prix doit être supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (!isDoubleValid(cost)) {
                    JOptionPane.showMessageDialog(null, "Le coût doit être supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (!isDoubleValid(size)) {
                    JOptionPane.showMessageDialog(null, "La taille doit être supérieure à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (!isIntValid(stock)) {
                    JOptionPane.showMessageDialog(null, "Le stock doit être supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else if (categoryComboBox.getSelectedIndex() < 1) {
                    JOptionPane.showMessageDialog(null, "Sélectionnez une catégorie", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {

                        Category category = controller.getCategoryById(categoryComboBox.getSelectedIndex());

                        Product product = new Product(nameField.getText(),
                                colorComboBox.getSelectedItem().toString(),
                                price,
                                cost,
                                size,
                                stock,
                                (dateField.getText().isBlank() ? LocalDate.parse(dateField.getText()) : null),
                                shippableCheckBox.isSelected(),
                                (descriptionTextArea.getText().isBlank() ? null : descriptionTextArea.getText()),
                                (imgLinkField.getText().isBlank() ? null : imgLinkField.getText()),
                                category,
                                category.getId());

                        JOptionPane.showMessageDialog(null, "Produit ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        clear();
                        controller.addProduct(product);
                    } catch (GetCategoryByIdException | AddProductException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, fieldName + " doit être un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    private Boolean isNameValid(String name) {
        return name.matches("^[a-zA-Z0-9éèà]{3,50}$");
    }

    private Boolean isDoubleValid(double number) {
        return number > 0;
    }

    private Boolean isIntValid(int number) {
        return number > 0;
    }


    private Boolean checkFields() {
        Boolean check = true;
        for (Component component : getComponents()) {
            if (component instanceof JTextField) {
                component.setBackground(new Color(237, 123, 133, 255));
                if (((JTextField) component).getText().isBlank() && component != imgLinkField && component != descriptionTextArea && component != dateField) {
                    check = false;
                } else
                    component.setBackground(Color.WHITE);
            }
        }
        return check;
    }

    private void clear() {
        for (Component component : getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }
}
