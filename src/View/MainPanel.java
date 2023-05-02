package View;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JLabel label, logoLablel;

    public MainPanel() {
        label = new JLabel("Bienvenue sur le logiciel de la Maison du Meuble !");
        logoLablel = new JLabel();
        setLogo("OrangeChair-01-01.png");
        add(label, BorderLayout.NORTH);
        add(logoLablel, BorderLayout.CENTER);
        new ThreadLogo(this).start();
    }

    public void setLogo(String logoUrl) {
        logoLablel.setIcon(urlToImage(logoUrl));
    }

    private ImageIcon urlToImage(String url) {
        return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
    }
}
