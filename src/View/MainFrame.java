package View;

import javax.swing.*;
import java.awt.*;
import Controller.*;

public class MainFrame extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenu searchMenu, productMenu, applicationMenu, aboutMenu, statisticsMenu;
    private JMenuItem exitProgramItem, aboutItem, statisticsItem, searchWhoBoughtItem, searchWhoSuppliedCategory, searchBoughtHistoryItem, productAddItem, productEditItem, productDeleteItem, productSearchItem, mainMenuItem;
    private ApplicationController applicationController;
    private AddProductPanel addProductPanel;
    private EditProductPanel editProductPanel;
    private RemoveProductPanel removeProductPanel;
    private SearchProductPanel searchProductPanel;
    private CustomersByProductPanel customersByProductPanel;
    private SearchCustomerWhoPurchasedProductPanel searchCustomerWhoPurchasedProductPanel;
    private SupplierByCategoryPanel supplierByCategoryPanel;
    private MainPanel mainPanel;
    public MainFrame() {
        super("My App", null);
        setLayout(new BorderLayout());
        setBounds(0, 0, 1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        applicationController = new ApplicationController();

        addProductPanel = new AddProductPanel();
        editProductPanel = new EditProductPanel();
        removeProductPanel = new RemoveProductPanel();
        searchProductPanel = new SearchProductPanel();
        customersByProductPanel = new CustomersByProductPanel();
        searchCustomerWhoPurchasedProductPanel = new SearchCustomerWhoPurchasedProductPanel();
        supplierByCategoryPanel = new SupplierByCategoryPanel();

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

        mainPanel = new MainPanel();

        exitProgramItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Application de gestion de produits", "A propos", JOptionPane.INFORMATION_MESSAGE));
        productAddItem.addActionListener(e -> {
            clearMainFrame();
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
            clearMainFrame();
            add(editProductPanel);
            editProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        productDeleteItem.addActionListener(e -> {
            clearMainFrame();
            add(removeProductPanel);
            removeProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        productSearchItem.addActionListener(e -> {
            clearMainFrame();
            add(searchProductPanel);
            revalidate();
            repaint();
        });

        searchBoughtHistoryItem.addActionListener(e -> {
            clearMainFrame();
            add(customersByProductPanel);
            customersByProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        searchWhoBoughtItem.addActionListener(e -> {
            clearMainFrame();
            add(searchCustomerWhoPurchasedProductPanel);
            searchCustomerWhoPurchasedProductPanel.updateComboBox();
            revalidate();
            repaint();
        });

        searchWhoSuppliedCategory.addActionListener(e -> {
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
