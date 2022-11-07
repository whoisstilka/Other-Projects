package com.company;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600; // Създаваме и задаваме стойности на различни типове данни
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = 18000;
    static final int DELAY = 80;
    final int[] x = new int[18000];
    final int[] y = new int[18000];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    String username;
    boolean running = false;
    Timer timer;
    Random random = new Random();
    JButton button;

    GamePanel(String username) { // Конструктор GamePanel
        this.username = username;
        this.setPreferredSize(new Dimension(600, 600)); // Задаваме размерите на JPanel-a
        this.setBackground(Color.BLACK); // Задаваме background цвета
        this.setFocusable(true); // JPanel-а може да се фокуриса
        this.addKeyListener(new GamePanel.MyKeyAdapter()); // Добавяме Key Listener
        this.startGame(); // Извикваме метод startGame()
        this.button = new JButton("Start Game");
        this.button.addActionListener(this); // Добавяме Action Listener
    }

    public void startGame() { // Метод startGame
        this.newApple();
        this.running = true; // Змята започва да се движи
        this.timer = new Timer(80, this); // Създаваме нов Таймер
        this.timer.start(); // Таймера стартира
    }

    public void paintComponent(Graphics g) { // Метод paintComponent
        super.paintComponent(g); // Oтпечатвамe компонента без да заменяме метода
        this.draw(g); // Компонентният обект, върху който рисуваме
    }

    public void draw(Graphics g) {
        if (this.running) { // Ако играта "върви"
            g.setColor(Color.red); // Задаваме цвят и форма на ябълките
            g.fillOval(this.appleX, this.appleY, 20, 20);

            for(int i = 0; i < this.bodyParts; ++i) { // За всяка изядена ябълка змята става по-голяма
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(this.x[i], this.y[i], 20, 20);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(this.x[i], this.y[i], 20, 20);
                }
            }

            g.setColor(Color.white); // Задаваме цвят, шрифт на текста, който показва резултата (изядените ябълки)
            g.setFont(new Font("UNI SANS", 1, 40));
            FontMetrics metrics = this.getFontMetrics(g.getFont());
            g.drawString("Score: " + this.applesEaten, (600 - metrics.stringWidth("Score: " + this.applesEaten)) / 2, g.getFont().getSize());
        } else {
            this.gameOver(g);
        }

    }

    public void newApple() { // Метод за разнообразно създаване на ябълки
        this.appleX = this.random.nextInt(30) * 20;
        this.appleY = this.random.nextInt(30) * 20;
    }

    public void move() { // Метод за движение на змята
        for(int i = this.bodyParts; i > 0; --i) {
            this.x[i] = this.x[i - 1];
            this.y[i] = this.y[i - 1];
        }

        switch(this.direction) { // посоки на движение
            case 'D':
                this.y[0] += 20;
                break;
            case 'L':
                this.x[0] -= 20;
                break;
            case 'R':
                this.x[0] += 20;
                break;
            case 'U':
                this.y[0] -= 20;
        }

    }

    public void checkApple() { // Метод за проверяване дали ябълката е изядена
        if (this.x[0] == this.appleX && this.y[0] == this.appleY) {
            ++this.bodyParts;
            ++this.applesEaten;
            this.newApple();
        }

    }

    public void checkCollisions() { // Метод за проверяване дали змята е захапала себе си
        for(int i = this.bodyParts; i > 0; --i) {
            if (this.x[0] == this.x[i] && this.y[0] == this.y[i]) {
                this.running = false;
            }
        }

        if (this.x[0] < 0) {
            this.running = false;
        }

        if (this.x[0] > 600) {
            this.running = false;
        }

        if (this.y[0] < 0) {
            this.running = false;
        }

        if (this.y[0] > 600) {
            this.running = false;
        }

        if (!this.running) {
            this.timer.stop();
        }

    }

    public void gameOver(Graphics g) { // Game Over метод, задаваме цвят, шрифт на Game Over текста и на Score-а
        g.setColor(Color.white);
        g.setFont(new Font("UNI SANS", 1, 40));
        FontMetrics metrics1 = this.getFontMetrics(g.getFont());
        g.drawString("Score: " + this.applesEaten, (600 - metrics1.stringWidth("Score: " + this.applesEaten)) / 2, g.getFont().getSize());
        g.setColor(Color.white);
        g.setFont(new Font("UNI SANS", 1, 75));
        FontMetrics metrics2 = this.getFontMetrics(g.getFont());
        g.drawString("Game Over", (600 - metrics2.stringWidth("Game Over")) / 2, 300);
        DatabaseAdapter.getInstance().updateScore(applesEaten, username);
    }

    public void actionPerformed(ActionEvent e) { // Action Performed метод, който проверява за всяко действие
        if (this.running) {
            this.move();
            this.checkApple();
            this.checkCollisions();
        }

        this.repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public MyKeyAdapter() {
        }

        public void keyPressed(KeyEvent e) { // Метод Key Pressed за задаване на логика в движенията на змята
            switch(e.getKeyCode()) {
                case 37:
                    if (GamePanel.this.direction != 'R') { // Ако змята не се движи надясно, значи се движи наляво и т.н.
                        GamePanel.this.direction = 'L';
                    }
                    break;
                case 38:
                    if (GamePanel.this.direction != 'D') {
                        GamePanel.this.direction = 'U';
                    }
                    break;
                case 39:
                    if (GamePanel.this.direction != 'L') {
                        GamePanel.this.direction = 'R';
                    }
                    break;
                case 40:
                    if (GamePanel.this.direction != 'U') {
                        GamePanel.this.direction = 'D';
                    }
            }

        }
    }
}