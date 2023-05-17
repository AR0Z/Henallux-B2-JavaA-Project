package View;

import Controller.ApplicationController;
import Exceptions.*;
import Model.Category;
import Model.Product;
import View.ComboBox.ComboBoxCategories;
import View.ComboBox.ComboBoxProducts;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditProductPanel extends JPanel {
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
    private ComboBoxProducts comboBoxProducts;
    private SimpleDateFormat dateFormat ;
    public EditProductPanel() {
        try {
            controller = new ApplicationController();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        setLayout(new GridLayout(14, 2, 5, 5));
        add(new JLabel("Menu d'édition de produit", SwingConstants.CENTER));
        comboBoxProducts = new ComboBoxProducts();
        comboBoxProducts.addActionListener(l -> {
            if (comboBoxProducts.getSelectedIndex() >= 1) {
                try {
                    Product product = controller.getProductById(comboBoxProducts.getId());
                    updateFields(product);
                } catch (GetProductByIdException e) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else clear();
        });
        add(comboBoxProducts);
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

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateLabel = new JLabel("Date d'ajout jj/mm/aaaa:", SwingConstants.RIGHT);
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


        submitButton = new JButton("Enregistrer");
        submitButton.addActionListener(e -> submit());
        add(submitButton);

        clearButton = new JButton("Effacer");
        clearButton.addActionListener(e -> clear());
        add(clearButton);
        setVisible(true);
    }

    public void updateFields(Product product) {
        nameField.setText(product.getName());
        colorComboBox.setSelectedItem(product.getColor());
        categoryComboBox.setSelectedIndex(product.getCategory_FK());
        priceField.setText(String.valueOf(product.getPrice()));
        costField.setText(String.valueOf(product.getCost()));
        sizeField.setText(String.valueOf(product.getSize()));
        stockField.setText(String.valueOf(product.getStock()));
        shippableCheckBox.setSelected(product.getShippable());
        dateField.setText(product.getAdditionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        descriptionTextArea.setText(product.getDescription());
        imgLinkField.setText(product.getImgLink());
    }

    private void submit(){
        if (!checkFields()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String errorMessage = "";
            double price = 0, cost = 0, size = 0;
            int stock = 0;

            if (!isNameValid(nameField.getText())) {
                errorMessage += "Le nom doit contenir entre 3 et 50 caractères (lettres et chiffres uniquement).\n";
            }

            try {
                price = Double.parseDouble(priceField.getText());

                if (!isDoubleValid(price)) {
                    errorMessage += "Le prix doit être supérieur à 0.\n";
                }

            } catch (NumberFormatException e) {
                errorMessage += "Le prix doit être un nombre.\n";
            }

            try {
                cost = Double.parseDouble(costField.getText());

                if (!isDoubleValid(cost)) {
                    errorMessage += "Le coût doit être supérieur à 0.\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Le coût doit être un nombre.\n";
            }

            try {
                size = Double.parseDouble(sizeField.getText());

                if (!isDoubleValid(size)) {
                    errorMessage += "La taille doit être supérieure à 0.\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "La taille doit être un nombre.\n";
            }

            try {
                stock = Integer.parseInt(stockField.getText());

                if (!isIntValid(stock)) {
                    errorMessage += "Le stock doit être supérieur à 0.\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Le stock doit être un nombre.\n";
            }

            if (categoryComboBox.getSelectedIndex() < 1) {
                errorMessage += "La sélection d'une catégorie est obligatoire.\n";
            }

            if (!validateDate()) {
                errorMessage += "Veuillez entrer une date valide (jj/mm/aaaa) postérieur a 2000.\n";
            }

            if (!errorMessage.isBlank()) {
                JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {

                try {
                    Category category = controller.getCategoryById(categoryComboBox.getSelectedIndex());

                    Product product = new Product(comboBoxProducts.getId(),
                            nameField.getText(),
                            colorComboBox.getSelectedItem().toString(),
                            price,
                            cost,
                            size,
                            stock,
                            LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            shippableCheckBox.isSelected(),
                            (descriptionTextArea.getText().isBlank() ? null : descriptionTextArea.getText()),
                            (imgLinkField.getText().isBlank() ? null : imgLinkField.getText()),
                            category,
                            category.getId());
                    controller.editProduct(product);
                    JOptionPane.showMessageDialog(null, "Produit modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    clear();
                } catch (GetCategoryByIdException | EditProductException | ValueException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
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
        comboBoxProducts.update();
        nameField.setText("");
        colorComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
        priceField.setText("");
        costField.setText("");
        sizeField.setText("");
        stockField.setText("");
        dateField.setText("");
        shippableCheckBox.setSelected(false);
        descriptionTextArea.setText("");
        imgLinkField.setText("");
    }

    private Boolean validateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Boolean check = true;
        try {
            if (dateFormat.parse(dateField.getText()).getYear() + 1900 < 2000) {
                check = false;
            }
        } catch (ParseException ex) {
            check = false;
        }
        return check;
    }
}

