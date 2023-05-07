package View;

import Controller.ApplicationController;
import Model.Customer;
import Model.SearchBoughtHistory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchBoughtHistoryItemPanel extends JPanel {
    private ApplicationController controller;
    private ArrayList<Customer> customers;
    private ArrayList<SearchBoughtHistory> searchBoughtHistories;
    private CustomerComboBox customerComboBox;
    private Customer customer;
    private JPanel topPanel, centerPanel;
    private String[] columnNames = {"ID de commande", "Quantité d'article", "Prix d'article", "Nom d'article", "Catégorie d'article"};
    JScrollPane scrollPane;
    public SearchBoughtHistoryItemPanel() {
        setLayout(new BorderLayout());

        topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Menu de recherche d'historique d'achat", SwingConstants.CENTER));
        controller = new ApplicationController();
        customers = controller.getAllCustomers();
        customerComboBox = new CustomerComboBox();
        customerComboBox.addActionListener(l -> {
            if (customerComboBox.getSelectedIndex() >= 1) {
                customer = customers.get(customerComboBox.getSelectedIndex() - 1);
                updateTable(customer);
            };
        });

        topPanel.add(customerComboBox);
        add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new FlowLayout());
        add(centerPanel, BorderLayout.CENTER);

        scrollPane = new JScrollPane();
        centerPanel.add(scrollPane);
    }

    public void updateTable(Customer customer){

        searchBoughtHistories = controller.searchBoughtHistory(customer);

        Object[][] data = new Object[searchBoughtHistories.size()][5];
        for (int i = 0; i < searchBoughtHistories.size(); i++) {
            data[i][0] = searchBoughtHistories.get(i).getOrderID();
            data[i][1] = searchBoughtHistories.get(i).getArticleQuantity();
            data[i][2] = searchBoughtHistories.get(i).getArticlePrice();
            data[i][3] = searchBoughtHistories.get(i).getArticleName();
            data[i][4] = searchBoughtHistories.get(i).getCategoryLabel();
        }

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        table.setFillsViewportHeight(true);

        scrollPane.setViewportView(table);

        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

    }
}
