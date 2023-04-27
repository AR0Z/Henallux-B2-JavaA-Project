package View;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JMenuBar mainMenuBar;
    private JMenu searchMenu, applicationMenu, aboutMenu, statisticsMenu;
    private JMenuItem exitProgramItem, aboutItem, statisticsItem, searchWhoBoughtItem, searchWhoSuppliedItem, searchBoughtHistoryItem;
    public MainFrame() {
        super("My App", null);
        setLayout(null);
        setBounds(0,0,500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a menu bar
        mainMenuBar = new JMenuBar();
        searchMenu = new JMenu("Rechercher");
        applicationMenu = new JMenu("Application");
        aboutMenu = new JMenu("A propos");
        statisticsMenu = new JMenu("Statistiques");

        // create menu items
        exitProgramItem = new JMenuItem("Quitter");
        aboutItem = new JMenuItem("Details");
        statisticsItem = new JMenuItem("Afficher");
        searchWhoBoughtItem = new JMenuItem("Acheteur du produit");
        searchWhoSuppliedItem = new JMenuItem("Fournisseur du produit");
        searchBoughtHistoryItem = new JMenuItem("Historique d'achat");

        searchMenu.add(searchWhoBoughtItem);
        searchMenu.add(searchWhoSuppliedItem);
        searchMenu.add(searchBoughtHistoryItem);
        applicationMenu.add(exitProgramItem);
        aboutMenu.add(aboutItem);
        statisticsMenu.add(statisticsItem);

        mainMenuBar.add(applicationMenu);
        mainMenuBar.add(searchMenu);
        mainMenuBar.add(statisticsMenu);
        mainMenuBar.add(aboutMenu);

        setJMenuBar(mainMenuBar);
        setVisible(true);
    }

}
