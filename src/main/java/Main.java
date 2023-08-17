import components.Dino;
import components.GamePanel;
import components.Ground;
import components.Obstacles;

import javax.swing.*;
import java.awt.*;

public class Main {

        JFrame mainWindow = new JFrame("My game");

        public static int WIDTH = 800;
        public static int HEIGHT = 500;

        public void createAndShowGUI() {
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = mainWindow.getContentPane();

            GamePanel gamePanel = new GamePanel(WIDTH,HEIGHT);
            gamePanel.addKeyListener(gamePanel);
            gamePanel.setFocusable(true);

            container.setLayout(new BorderLayout());

            container.add(gamePanel, BorderLayout.CENTER);

            mainWindow.setSize(WIDTH, HEIGHT);
            mainWindow.setResizable(false);
            mainWindow.setVisible(true);

            Dino dino = new Dino();
            dino.setJumpFactor(20);
            Ground ground = new Ground(HEIGHT);

            gamePanel.setObstacleIntervalAndSpeed(200, 7);
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }
}