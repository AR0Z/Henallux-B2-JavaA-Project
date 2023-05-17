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
                } catch (CloseConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
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
        applicationMenu = new JMenu("Application");
        statisticsMenu = new JMenu("Statistiques");
        productMenu = new JMenu("Produits");

        // create menu items
        exitProgramItem = new JMenuItem("Quitter");
        mainMenuItem = new JMenuItem("Accueil");
        statisticsItem = new JMenuItem("Afficher");
        searchWhoBoughtItem = new JMenuItem("Acheteur du produit");
        searchWhoSuppliedCategory = new JMenuItem("Fournisseurs d'une categorie");
        searchBoughtHistoryItem = new JMenuItem("Historique d'achat d'un client");

        productAddItem = new JMenuItem("Ajouter");
        productEditItem = new JMenuItem("Modifier");
        productDeleteItem = new JMenuItem("Supprimer");
        productListingItem = new JMenuItem("Lister");

        searchMenu.add(searchWhoBoughtItem);
        searchMenu.add(searchWhoSuppliedCategory);
        searchMenu.add(searchBoughtHistoryItem);
        applicationMenu.add(mainMenuItem);
        applicationMenu.add(exitProgramItem);
        statisticsMenu.add(statisticsItem);

        productMenu.add(productAddItem);
        productMenu.add(productEditItem);
        productMenu.add(productDeleteItem);
        productMenu.add(productListingItem);


        mainMenuBar.add(applicationMenu);
        mainMenuBar.add(productMenu);
        mainMenuBar.add(searchMenu);
        mainMenuBar.add(statisticsMenu);

        MainPanel mainPanel = new MainPanel();

        exitProgramItem.addActionListener(event -> {
            try{
                controller.closeConnection();
            } catch (CloseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
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
