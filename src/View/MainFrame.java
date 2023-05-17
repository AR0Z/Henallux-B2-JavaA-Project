package View;

import Controller.ApplicationController;
import Exceptions.CloseConnectionException;
import Exceptions.ConnectionException;
import Exceptions.DBExceptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenu searchMenu, productMenu, applicationMenu, statisticsMenu;
    private JMenuItem exitProgramItem, statisticsItem, searchWhoBoughtItem, searchWhoSuppliedCategory, searchBoughtHistoryItem, productAddItem, productEditItem, productDeleteItem, productListingItem, mainMenuItem;
    private ApplicationController controller;
    public MainFrame() {
        super("My App", null);
        setLayout(new BorderLayout());
        setBounds(0, 0, 1000, 500);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                try{
                    controller.closeConnection();
                    System.exit(0);
                } catch (CloseConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        try {
            controller = new ApplicationController();
        } catch (ConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        // create a menu bar
        mainMenuBar = new JMenuBar();

        searchMenu = new JMenu("Rechercher");
        mainMenuBar.add(searchMenu);
        applicationMenu = new JMenu("Application");
        mainMenuBar.add(applicationMenu);
        statisticsMenu = new JMenu("Statistiques");
        mainMenuBar.add(statisticsMenu);
        productMenu = new JMenu("Produits");
        mainMenuBar.add(productMenu);

        // create menu items
        exitProgramItem = new JMenuItem("Quitter");
        applicationMenu.add(exitProgramItem);

        mainMenuItem = new JMenuItem("Accueil");
        applicationMenu.add(mainMenuItem);

        statisticsItem = new JMenuItem("Afficher");
        statisticsMenu.add(statisticsItem);

        searchWhoBoughtItem = new JMenuItem("Acheteur du produit");
        searchMenu.add(searchWhoBoughtItem);
        searchWhoSuppliedCategory = new JMenuItem("Fournisseurs d'une categorie");
        searchMenu.add(searchWhoSuppliedCategory);
        searchBoughtHistoryItem = new JMenuItem("Historique d'achat d'un client");
        searchMenu.add(searchBoughtHistoryItem);

        productAddItem = new JMenuItem("Ajouter");
        productMenu.add(productAddItem);
        productEditItem = new JMenuItem("Modifier");
        productMenu.add(productEditItem);
        productDeleteItem = new JMenuItem("Supprimer");
        productMenu.add(productDeleteItem);
        productListingItem = new JMenuItem("Lister");
        productMenu.add(productListingItem);

        MainPanel mainPanel = new MainPanel();

        exitProgramItem.addActionListener(event -> {
            try{
                controller.closeConnection();
                System.exit(0);
            } catch (CloseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        productAddItem.addActionListener(e -> {
            clearMainFrame();
            AddProductPanel addProductPanel = new AddProductPanel();
            add(addProductPanel);
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
            revalidate();
            repaint();
        });

        productDeleteItem.addActionListener(e -> {
            RemoveProductPanel removeProductPanel = new RemoveProductPanel();
            clearMainFrame();
            add(removeProductPanel);
            revalidate();
            repaint();
        });

        productListingItem.addActionListener(e -> {
            ListingProductPanel listingProductPanel = new ListingProductPanel();
            clearMainFrame();
            add(listingProductPanel);
            revalidate();
            repaint();
        });

        searchBoughtHistoryItem.addActionListener(e -> {
            PurchasesByCustomerPanel purchasesByCustomerPanel = new PurchasesByCustomerPanel();
            clearMainFrame();
            add(purchasesByCustomerPanel);
            revalidate();
            repaint();
        });

        searchWhoBoughtItem.addActionListener(e -> {
            CustomerByProductPanel customerByProductPanel = new CustomerByProductPanel();
            clearMainFrame();
            add(customerByProductPanel);
            revalidate();
            repaint();
        });

        searchWhoSuppliedCategory.addActionListener(e -> {
            SupplierByCategoryPanel supplierByCategoryPanel = new SupplierByCategoryPanel();
            clearMainFrame();
            add(supplierByCategoryPanel);
            revalidate();
            repaint();
        });

        statisticsItem.addActionListener(e -> {
            StatisticsPanel statisticsPanel = new StatisticsPanel();
            clearMainFrame();
            add(statisticsPanel);
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
