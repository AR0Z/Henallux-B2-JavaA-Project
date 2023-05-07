package View;

import Controller.ApplicationController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditProductPanel extends JPanel {
    private JLabel nameLabel, colorLabel, priceLabel, costLabel, sizeLabel, stockLabel, shippableLabel, descriptionLabel, imgLinkLabel, categoryLabel;
    private JTextField nameField, priceField, costField, sizeField, stockField, imgLinkField;
    private JTextArea descriptionTextArea;
    private JComboBox<String> colorComboBox, categoryComboBox, productComboBox;
    private JCheckBox shippableCheckBox;
    private ApplicationController applicationController;
    private JButton submitButton, clearButton;
    private ArrayList<Category> categories;
    private JScrollPane scrollPane;
    private ArrayList<Product> products;

    public EditProductPanel() {
        applicationController = new ApplicationController();
        setLayout(new GridLayout(12, 2, 5, 5));
        add(new JLabel("Menu d'édition de produit", SwingConstants.CENTER));
        products = applicationController.getAllProducts();
        productComboBox = new JComboBox<>();
        productComboBox.addItem("Choisir un produit");
        for (Product product : products) {
            productComboBox.addItem(product.getName());
        }
        productComboBox.addActionListener(l -> {
            if (productComboBox.getSelectedIndex() >= 1) {
                Product product = products.get(productComboBox.getSelectedIndex() - 1);
                updateFields(product);
            } else clear();
        });
        add(productComboBox);
        nameLabel = new JLabel("*Name:", SwingConstants.RIGHT);
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        colorLabel = new JLabel("*Color:", SwingConstants.RIGHT);
        colorComboBox = new JComboBox<>(new String[]{"Rouge", "Bleu", "Vert", "Jaune", "Noir", "Blanc", "Rose", "Gris", "Orange", "Marron"});
        add(colorLabel);
        add(colorComboBox);

        categories = applicationController.getAllCategories();
        categoryLabel = new JLabel("*Category:", SwingConstants.RIGHT);
        categoryComboBox = new JComboBox<>();
        for (Category category : categories) {
            categoryComboBox.addItem(category.getLabel());
        }
        add(categoryLabel);
        add(categoryComboBox);

        priceLabel = new JLabel("*Price:", SwingConstants.RIGHT);
        priceField = new JTextField();
        add(priceLabel);
        add(priceField);

        costLabel = new JLabel("*Cost:", SwingConstants.RIGHT);
        costField = new JTextField();
        add(costLabel);
        add(costField);

        sizeLabel = new JLabel("*Size:", SwingConstants.RIGHT);
        sizeField = new JTextField();
        add(sizeLabel);
        add(sizeField);

        stockLabel = new JLabel("*Stock:", SwingConstants.RIGHT);
        stockField = new JTextField();
        add(stockLabel);
        add(stockField);

        shippableLabel = new JLabel("*Is shippable:", SwingConstants.RIGHT);
        shippableCheckBox = new JCheckBox();
        add(shippableLabel);
        add(shippableCheckBox);

        descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setLineWrap(true);
        scrollPane = new JScrollPane(descriptionTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(descriptionLabel);
        add(scrollPane);

        imgLinkLabel = new JLabel("Image link:", SwingConstants.RIGHT);
        imgLinkField = new JTextField();
        add(imgLinkLabel);
        add(imgLinkField);


        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submit());
        add(submitButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clear());
        add(clearButton);
        setVisible(true);
    }

    public void updateFields(Product product) {
        nameField.setText(product.getName());
        colorComboBox.setSelectedItem(product.getColor());
        categoryComboBox.setSelectedIndex(products.indexOf(product.getCategory()) + 1);
        priceField.setText(String.valueOf(product.getPrice()));
        costField.setText(String.valueOf(product.getCost()));
        sizeField.setText(String.valueOf(product.getSize()));
        stockField.setText(String.valueOf(product.getStock()));
        shippableCheckBox.setSelected(product.getShippable());
        descriptionTextArea.setText(product.getDescription());
        imgLinkField.setText(product.getImgLink());
    }

    private void submit() {
        Product baseProduct = products.get(productComboBox.getSelectedIndex() + 1);
        Product product = null;
        try {
            if (!checkFields())
                throw new Exception("Veuillez remplir tous les champs obligatoire");
            String name = nameField.getText();
            if (!isNameValid(name))
                throw new Exception("Le nom doit contenir entre 3 et 50 caractères (lettres et chiffres uniquement)");
            String color = colorComboBox.getSelectedItem().toString();
            double price = Double.parseDouble(priceField.getText());
            if (!isDoubleValid(price))
                throw new Exception("Le prix doit être supérieur à 0");
            double cost = Double.parseDouble(costField.getText());
            if (!isDoubleValid(cost))
                throw new Exception("Le coût doit être supérieur à 0");
            double size = Double.parseDouble(sizeField.getText());
            if (!isDoubleValid(size))
                throw new Exception("La taille doit être supérieure à 0");
            int stock = Integer.parseInt(stockField.getText());
            if (!isIntValid(stock))
                throw new Exception("Le stock doit être supérieur à 0");
            Boolean shippable = shippableCheckBox.isSelected();
            String description = descriptionTextArea.getText();
            String imgLink = imgLinkField.getText();
            Category category = categories.get(categoryComboBox.getSelectedIndex());
            product = new Product(baseProduct.getId() - 2, name, color, price, cost, size, stock, baseProduct.getAdditionDate(), shippable, description, imgLink, category, category.getId());
            JOptionPane.showMessageDialog(null, "Produit modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        applicationController.editProduct(product);
        updateProducts();
    }

    private Boolean isNameValid(String name) {
        return name.matches("^[a-zA-Z0-9]{3,50}$");
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
                if (((JTextField) component).getText().isBlank() && component != imgLinkField && component != descriptionTextArea) {
                    check = false;
                } else
                    component.setBackground(Color.WHITE);
            }
        }
        return check;
    }

    public void clear() {
        // productComboBox.setSelectedIndex(0);
        nameField.setText("");
        colorComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
        priceField.setText("");
        costField.setText("");
        sizeField.setText("");
        stockField.setText("");
        shippableCheckBox.setSelected(false);
        descriptionTextArea.setText("");
        imgLinkField.setText("");
    }

    public void updateProducts() {
        products.removeAll(products);
        products = applicationController.getAllProducts();
        productComboBox.removeAllItems();
        productComboBox.addItem("Choisir un produit");
        for (Product product : products) {
            productComboBox.addItem(product.getName());
        }
    }
}
