package View;

import Controller.ApplicationController;
import Exceptions.DBExceptions;
import Exceptions.ValueException;
import Model.Product;

import javax.swing.*;
import java.awt.*;

public class EditProductPanel extends JPanel {
    private JLabel nameLabel, colorLabel, priceLabel, costLabel, sizeLabel, stockLabel, shippableLabel, descriptionLabel, imgLinkLabel, categoryLabel;
    private JTextField nameField, priceField, costField, sizeField, stockField, imgLinkField;
    private JTextArea descriptionTextArea;
    private JComboBox<String> colorComboBox;
    private ComboBoxCategories categoryComboBox;
    private JCheckBox shippableCheckBox;
    private ApplicationController controller;
    private JButton submitButton, clearButton;
    private JScrollPane scrollPane;

    private ComboBoxProducts comboBoxProducts;

    public EditProductPanel() {
        controller = new ApplicationController();
        setLayout(new GridLayout(12, 2, 5, 5));
        add(new JLabel("Menu d'édition de produit", SwingConstants.CENTER));
        comboBoxProducts = new ComboBoxProducts();
        comboBoxProducts.addActionListener(l -> {
            if (comboBoxProducts.getSelectedIndex() >= 1) {
                try {
                    Product product = controller.getProductById(comboBoxProducts.getId());
                    updateFields(product);
                } catch (DBExceptions e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else clear();
        });
        add(comboBoxProducts);
        nameLabel = new JLabel("*Name:", SwingConstants.RIGHT);
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        colorLabel = new JLabel("*Color:", SwingConstants.RIGHT);
        colorComboBox = new JComboBox<>(new String[]{"Rouge", "Bleu", "Vert", "Jaune", "Noir", "Blanc", "Rose", "Gris", "Orange", "Marron"});
        add(colorLabel);
        add(colorComboBox);

        categoryLabel = new JLabel("*Category:", SwingConstants.RIGHT);
        categoryComboBox = new ComboBoxCategories();

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
        categoryComboBox.setSelectedIndex(product.getCategory_FK() - 1);
        priceField.setText(String.valueOf(product.getPrice()));
        costField.setText(String.valueOf(product.getCost()));
        sizeField.setText(String.valueOf(product.getSize()));
        stockField.setText(String.valueOf(product.getStock()));
        shippableCheckBox.setSelected(product.getShippable());
        descriptionTextArea.setText(product.getDescription());
        imgLinkField.setText(product.getImgLink());
    }

    public void updateComboBox() {
        comboBoxProducts.update();
        categoryComboBox.update();
    }

    private void submit() {
        try {
            Product baseProduct = controller.getProductById(comboBoxProducts.getId());
            Product product = null;
            try {
                product = validProduct(product, baseProduct);
                JOptionPane.showMessageDialog(null, "Produit modifié avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            controller.editProduct(product);
            comboBoxProducts.update();
        } catch (DBExceptions e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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

    private void clear() {
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

    private Product validProduct(Product product, Product baseProduct) throws ValueException {
        try {
            product = new Product();
            if (!checkFields())
                throw new ValueException("Veuillez remplir tous les champs obligatoire");
            product.setName(nameField.getText());
            if (!isNameValid(product.getName()))
                throw new ValueException("Le nom doit contenir entre 3 et 50 caractères (lettres et chiffres uniquement)");
            product.setColor(colorComboBox.getSelectedItem().toString());
            product.setPrice(Double.parseDouble(priceField.getText()));
            if (!isDoubleValid(product.getPrice()))
                throw new ValueException("Le prix doit être supérieur à 0");
            product.setCost(Double.parseDouble(costField.getText()));
            if (!isDoubleValid(product.getCost()))
                throw new ValueException("Le coût doit être supérieur à 0");
            product.setSize(Double.parseDouble(sizeField.getText()));
            if (!isDoubleValid(product.getSize()))
                throw new ValueException("La taille doit être supérieure à 0");
            product.setStock(Integer.parseInt(stockField.getText()));
            if (!isIntValid(product.getStock()))
                throw new ValueException("Le stock doit être supérieur à 0");
            product.setShippable(shippableCheckBox.isSelected());
            product.setDescription(descriptionTextArea.getText());
            product.setImgLink(imgLinkField.getText());
            product.setCategory(controller.getCategoryById(categoryComboBox.getId()));
            product.setId(baseProduct.getId());
            product.setAdditionDate(baseProduct.getAdditionDate());
            product.setCategory_FK(baseProduct.getCategory_FK());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }
}
