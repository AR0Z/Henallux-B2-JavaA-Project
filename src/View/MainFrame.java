package View;

import Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenu searchMenu, productMenu, applicationMenu, aboutMenu, statisticsMenu;
    private JMenuItem exitProgramItem, aboutItem, statisticsItem, searchWhoBoughtItem, searchWhoSuppliedCategory, searchBoughtHistoryItem, productAddItem, productEditItem, productDeleteItem, productSearchItem, mainMenuItem;

    public MainFrame() {
        super("My App", null);
        setLayout(new BorderLayout());
        setBounds(0, 0, 1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a menu bar
        mainMenuBar = new JMenuBar();
        searchMenu = new JMenu("Rechercher");
        applicationMenu = new JMenu("Application");
        aboutMenu = new JMenu("A propos");
        statisticsMenu = new JMenu("Statistiques");
        productMenu = new JMenu("Produits");

        // create menu items
        exitProgramItem = new JMenuItem("Quitter");
        mainMenuItem = new JMenuItem("Accueil");
        aboutItem = new JMenuItem("Details");
        statisticsItem = new JMenuItem("Afficher");
        searchWhoBoughtItem = new JMenuItem("Acheteur du produit");
        searchWhoSuppliedCategory = new JMenuItem("Fournisseurs d'une categorie");
        searchBoughtHistoryItem = new JMenuItem("Historique d'achat d'un client");

        productAddItem = new JMenuItem("Ajouter");
        productEditItem = new JMenuItem("Modifier");
        productDeleteItem = new JMenuItem("Supprimer");
        productSearchItem = new JMenuItem("Rechercher");

        searchMenu.add(searchWhoBoughtItem);
        searchMenu.add(searchWhoSuppliedCategory);
        searchMenu.add(searchBoughtHistoryItem);
        applicationMenu.add(mainMenuItem);
        applicationMenu.add(exitProgramItem);
        aboutMenu.add(aboutItem);
        statisticsMenu.add(statisticsItem);

        productMenu.add(productAddItem);
        productMenu.add(productEditItem);
        productMenu.add(productDeleteItem);
        productMenu.add(productSearchItem);


        mainMenuBar.add(applicationMenu);
        mainMenuBar.add(productMenu);
        mainMenuBar.add(searchMenu);
        mainMenuBar.add(statisticsMenu);
        mainMenuBar.add(aboutMenu);

        MainPanel mainPanel = new MainPanel();

        exitProgramItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Application de gestion de produits", "A propos", JOptionPane.INFORMATION_MESSAGE));
        productAddItem.addActionListener(e -> {
            clearMainFrame();
            AddProductPanel addProductPanel = new AddProductPanel();
            add(addProductPanel);
            addProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        mainMenuItem.addActionListener(e -> {
            clearMainFrame();
            add(mainPanel);
            revalidate();
            repaint();
        });

        productEditItem.addActionListener(e -> {
            EditProductPanel editProductPanel = new EditProductPanel();
            clearMainFrame();
            add(editProductPanel);
            editProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        productDeleteItem.addActionListener(e -> {
            RemoveProductPanel removeProductPanel = new RemoveProductPanel();
            clearMainFrame();
            add(removeProductPanel);
            removeProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        productSearchItem.addActionListener(e -> {
            SearchProductPanel searchProductPanel = new SearchProductPanel();
            clearMainFrame();
            add(searchProductPanel);
            revalidate();
            repaint();
        });

        searchBoughtHistoryItem.addActionListener(e -> {
            CustomersByProductPanel customersByProductPanel = new CustomersByProductPanel();
            clearMainFrame();
            add(customersByProductPanel);
            customersByProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        searchWhoBoughtItem.addActionListener(e -> {
            SearchCustomerWhoPurchasedProductPanel searchCustomerWhoPurchasedProductPanel = new SearchCustomerWhoPurchasedProductPanel();
            clearMainFrame();
            add(searchCustomerWhoPurchasedProductPanel);
            searchCustomerWhoPurchasedProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        searchWhoSuppliedCategory.addActionListener(e -> {
            SupplierByCategoryPanel supplierByCategoryPanel = new SupplierByCategoryPanel();
            clearMainFrame();
            add(supplierByCategoryPanel);
            supplierByCategoryPanel.updateComboBox();
            revalidate();
            repaint();
        });

        add(mainPanel);

        setJMenuBar(mainMenuBar);
        setVisible(true);
    }

    public void clearMainFrame() {
        for (Component component : this.getContentPane().getComponents()) {
            if (component instanceof JPanel)
                remove(component);
        }
        revalidate();
        repaint();
    }
}
