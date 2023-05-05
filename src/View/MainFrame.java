package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenu searchMenu, productMenu, applicationMenu, aboutMenu, statisticsMenu;
    private JMenuItem exitProgramItem, aboutItem, statisticsItem, searchWhoBoughtItem, searchWhoSuppliedItem, searchBoughtHistoryItem, productAddItem, productEditItem, productDeleteItem, productSearchItem;

    public MainFrame() {
        super("My App", null);
        setLayout(new BorderLayout());
        setBounds(0, 0, 500, 500);
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
        aboutItem = new JMenuItem("Details");
        statisticsItem = new JMenuItem("Afficher");
        searchWhoBoughtItem = new JMenuItem("Acheteur du produit");
        searchWhoSuppliedItem = new JMenuItem("Fournisseur du produit");
        searchBoughtHistoryItem = new JMenuItem("Historique d'achat");

        productAddItem = new JMenuItem("Ajouter");
        productEditItem = new JMenuItem("Modifier");
        productDeleteItem = new JMenuItem("Supprimer");
        productSearchItem = new JMenuItem("Rechercher");

        searchMenu.add(searchWhoBoughtItem);
        searchMenu.add(searchWhoSuppliedItem);
        searchMenu.add(searchBoughtHistoryItem);
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

        exitProgramItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Application de gestion de produits", "A propos", JOptionPane.INFORMATION_MESSAGE));

        add(new MainPanel());

        setJMenuBar(mainMenuBar);
        setVisible(true);
    }

}
