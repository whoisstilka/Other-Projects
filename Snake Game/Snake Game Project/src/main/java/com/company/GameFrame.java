package com.company;

import java.awt.Component;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame(String username) {
        this.add(new GamePanel(username));
        this.setTitle("Snake");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo((Component)null);
    }
}