package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, Runnable {
  
  public static int WIDTH;
  public static int HEIGHT;
  public Thread animator;

  public boolean running = false;
  public boolean gameOver = false;

  public Ground ground;
  public Dino dino;
  public Obstacles obstacles;

  public int score;

  
  public GamePanel(int width, int height) {
    WIDTH = width;
    HEIGHT = height;
    
    ground = new Ground(HEIGHT);
    dino = new Dino();
    obstacles = new Obstacles((int)(WIDTH * 1.5));

    score = 0;
    
    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    g.setFont(new Font("Courier New", Font.BOLD, 25));
    g.drawString(Integer.toString(score), getWidth()/2 - 5, 100);
    ground.create(g);
    dino.create(g);
    obstacles.create(g);
  }

  public void run() {
    running = true;

    while(running) {
      updateGame();
      repaint();
      try {
        Thread.sleep(50);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
      // Добавьте эту строку для обновления препятствий
      obstacles.update();
    }
  }

  public void updateGame() {
    score += 1;

    ground.update();
    obstacles.update();

    if(obstacles.hasCollided()) {
      System.out.println("Collided!");
      dino.die();
      repaint();
      running = false;
      gameOver = true;

      // Вызов метода reset() или другие действия
      reset();  // Вам нужно добавить метод reset(), если он отсутствует
    }
    // game complete condition
  }

  public void reset() {
    score = 0;
    System.out.println("reset");
    obstacles.resume();
    gameOver = false;
  }
  
  public void keyTyped(KeyEvent e) {
    // System.out.println(e);
    if(e.getKeyChar() == ' ') {    
      if(gameOver) reset();
      if (animator == null || !running) {
        System.out.println("Game starts");        
        animator = new Thread(this);
        animator.start();     
        dino.startRunning();   
      } else {
        dino.jump();
      }
    }
  }
  
  public void keyPressed(KeyEvent e) {
    // System.out.println("keyPressed: "+e);
  }
  
  public void keyReleased(KeyEvent e) {
    // System.out.println("keyReleased: "+e);
  }
  public void setObstacleIntervalAndSpeed(int obstacleInterval, int movementSpeed) {
    obstacles.setObstacleIntervalAndSpeed(obstacleInterval, movementSpeed);
  }
}