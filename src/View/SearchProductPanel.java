package View;

import javax.swing.*;

public class SearchProductPanel extends JPanel {

    private JComboBox<String> searchByComboBox;

    public SearchProductPanel() {
        add(new JLabel("Rechercher un produit"));
        searchByComboBox = new JComboBox<>(new String[]{"Par nom", "Par référence"});
        add(searchByComboBox);
        add(new JTextField(20));
        add(new JButton("Rechercher"));
    }
}
