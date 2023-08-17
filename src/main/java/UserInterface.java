import components.Dino;
import components.Ground;
import components.Obstacles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class UserInterface {
  JFrame mainWindow = new JFrame("T-Rex Run");

  public static int WIDTH = 800;
  public static int HEIGHT = 500;

  public void createAndShowGUI() {
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container container = mainWindow.getContentPane();

    GamePanel gamePanel = new GamePanel();
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(true);

    container.setLayout(new BorderLayout());

    container.add(gamePanel, BorderLayout.CENTER);

    mainWindow.setSize(WIDTH, HEIGHT);
    mainWindow.setResizable(false);
    mainWindow.setVisible(true);

    // Создаем экземпляры Dino, Ground и Obstacles и настраиваем их свойства
    Dino dino = new Dino();
    dino.setJumpFactor(15);
//    dino.setImagePaths("путьКИзображениюСтойки", "путьКИзображениюЛевойНоги", "путьКИзображениюПравойНоги", "путьКИзображениюСмерти");

    Ground ground = new Ground(HEIGHT);
//    ground.setImagePath("путьКИзображениюЗемли");

    Obstacles obstacles = new Obstacles(300); // Например, 300 - это начальная позиция по x
//    ArrayList<String> obstacleImagePaths = new ArrayList<>();
//    obstacleImagePaths.add("путьКИзображениюПрепятствия1");
//    obstacleImagePaths.add("путьКИзображениюПрепятствия2");
//    obstacles.setImagePaths(obstacleImagePaths);
      obstacles.setObstacleIntervalAndSpeed(50, 5);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new UserInterface().createAndShowGUI();
      }
    });
  }
}