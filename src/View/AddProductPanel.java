package View;

import Controller.ApplicationController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddProductPanel extends JPanel {
    private JLabel nameLabel, colorLabel, priceLabel, costLabel, sizeLabel, stockLabel, shippableLabel, descriptionLabel, imgLinkLabel, categoryLabel;
    private JTextField nameField, priceField, costField, sizeField, stockField, imgLinkField;
    private JTextArea descriptionTextArea;
    private JComboBox<String> colorComboBox, categoryComboBox;
    private JCheckBox shippableCheckBox;
    private ApplicationController applicationController;
    private JButton submitButton, clearButton;
    private ArrayList<Category> categories;
    private JScrollPane scrollPane;

    public AddProductPanel() {
        setLayout(new GridLayout(12, 2, 5, 5));
        add(new JLabel("Menu d'ajout de produit", SwingConstants.CENTER));
        add(new JLabel("Les champs marqués d'une * sont obligatoires", SwingConstants.CENTER));

        applicationController = new ApplicationController();

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

    private void submit() {
        Product product = null;
        try {
            product = validProduct(product);
            JOptionPane.showMessageDialog(null, "Produit ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        applicationController.addProduct(product);
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
        for (Component component : getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

    private Product validProduct(Product product) throws Exception{
        product = new Product();
        if (!checkFields())
            throw new Exception("Veuillez remplir tous les champs obligatoire");
        product.setName(nameField.getText());
        if (!isNameValid(product.getName()))
            throw new Exception("Le nom doit contenir entre 3 et 50 caractères (lettres et chiffres uniquement)");
        product.setColor(colorComboBox.getSelectedItem().toString());
        product.setPrice(Double.parseDouble(priceField.getText()));
        if (!isDoubleValid(product.getPrice()))
            throw new Exception("Le prix doit être supérieur à 0");
        product.setCost(Double.parseDouble(costField.getText()));
        if (!isDoubleValid(product.getCost()))
            throw new Exception("Le coût doit être supérieur à 0");
        product.setSize(Double.parseDouble(sizeField.getText()));
        if (!isDoubleValid(product.getSize()))
            throw new Exception("La taille doit être supérieure à 0");
        product.setStock(Integer.parseInt(stockField.getText()));
        if (!isIntValid(product.getStock()))
            throw new Exception("Le stock doit être supérieur à 0");
        product.setShippable(shippableCheckBox.isSelected());
        product.setDescription(descriptionTextArea.getText());
        product.setImgLink(imgLinkField.getText());
        product.setCategory(categories.get(categoryComboBox.getSelectedIndex() + 1));
        product.setId(-1);
        product.setAdditionDate(null);
        product.setCategory_FK(categories.get(categoryComboBox.getSelectedIndex() + 1).getId());
        return product;
    }
}
