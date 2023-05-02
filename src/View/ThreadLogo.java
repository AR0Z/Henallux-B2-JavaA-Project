package View;

public class ThreadLogo extends Thread {
    private MainPanel mainPanel;
    private int i = 0;

    public ThreadLogo(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void run() {
        int i = 1;
        while (true) {
            try {
                Thread.sleep(100);
                mainPanel.setLogo("ressources/" + i + ".png");
                i++;
                if (i == 25) {
                    i = 1;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
