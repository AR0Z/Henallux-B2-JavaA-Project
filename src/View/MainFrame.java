package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenu searchMenu, productMenu, applicationMenu, aboutMenu, statisticsMenu;
    private JMenuItem exitProgramItem, aboutItem, statisticsItem, searchWhoBoughtItem, searchWhoSuppliedCategory, searchBoughtHistoryItem, productAddItem, productEditItem, productDeleteItem, productListingItem, mainMenuItem;

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
        productListingItem = new JMenuItem("Lister");

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
        productMenu.add(productListingItem);


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
            CustomersByProductPanel customersByProductPanel = new CustomersByProductPanel();
            clearMainFrame();
            add(customersByProductPanel);
            revalidate();
            repaint();
        });

        searchWhoBoughtItem.addActionListener(e -> {
            SearchCustomerWhoPurchasedProductPanel searchCustomerWhoPurchasedProductPanel = new SearchCustomerWhoPurchasedProductPanel();
            clearMainFrame();
            add(searchCustomerWhoPurchasedProductPanel);
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
