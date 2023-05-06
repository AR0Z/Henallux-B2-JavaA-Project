package View;

import Controller.ApplicationController;
import Model.Category;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddProductPanel extends JPanel {
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel colorLabel;
    private JComboBox<String> colorComboBox;
    private JLabel priceLabel;
    private JTextField priceField;
    private JLabel costLabel;
    private JTextField costField;
    private JLabel sizeLabel;
    private JTextField sizeField;
    private JLabel stockLabel;
    private JTextField stockField;
    private JLabel shippableLabel;
    private JCheckBox shippableCheckBox;
    private JLabel descriptionLabel;
    private JTextArea descriptionTextArea;
    private JLabel imgLinkLabel;
    private JTextField imgLinkField;
    private JLabel categoryLabel;
    private JComboBox<String> categoryComboBox;

    private ApplicationController applicationController;

    public AddProductPanel() {
        setLayout(new GridLayout(11, 2, 5, 5)); // 11 rows, 2 columns, 5px horizontal and vertical gaps

        applicationController = new ApplicationController();

        nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        colorLabel = new JLabel("Color:");
        colorComboBox = new JComboBox<>(new String[]{"Red", "Green", "Blue"});
        add(colorLabel);
        add(colorComboBox);

        priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        add(priceLabel);
        add(priceField);

        costLabel = new JLabel("Cost:");
        costField = new JTextField();
        add(costLabel);
        add(costField);

        sizeLabel = new JLabel("Size:");
        sizeField = new JTextField();
        add(sizeLabel);
        add(sizeField);

        stockLabel = new JLabel("Stock:");
        stockField = new JTextField();
        add(stockLabel);
        add(stockField);

        shippableLabel = new JLabel("Is shippable:");
        shippableCheckBox = new JCheckBox();
        add(shippableLabel);
        add(shippableCheckBox);

        descriptionLabel = new JLabel("Description:");
        descriptionTextArea = new JTextArea();
        add(descriptionLabel);
        add(descriptionTextArea);

        imgLinkLabel = new JLabel("Image link:");
        imgLinkField = new JTextField();
        add(imgLinkLabel);
        add(imgLinkField);

        ArrayList<Category> categories = applicationController.getAllCategories();
        categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>();
        for (Category category : categories) {
            categoryComboBox.addItem(category.getLabel());
        }
        add(categoryLabel);
        add(categoryComboBox);
    }
}
