package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Login extends JFrame {
    JFrame loginFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JLabel mainTitleLabel;
    JLabel background;
    JLabel needHelpLabel;
    JButton playButton;
    JLabel highScore;
    JLabel highScoreOutput;
    JLabel logOut;
    String username;
    JLabel snake;


    Login(String username) {
        this.username = username;

        mainTitleLabel = new JLabel();
        mainTitleLabel.setText("Simple Snake Game");
        mainTitleLabel.setFont(new Font("UniSansBold", Font.BOLD, 40));
        mainTitleLabel.setForeground(Color.WHITE);
        mainTitleLabel.setBounds(150, 15, 350, 45);

        needHelpLabel = new JLabel();
        needHelpLabel.setText("Need Help?");
        needHelpLabel.setFont(new Font("UniSansBold", Font.BOLD, 15));
        needHelpLabel.setForeground(Color.LIGHT_GRAY);
        needHelpLabel.setBounds(20, 417, 100, 16);

        needHelpLabel = new JLabel();
        needHelpLabel.setText("Need Help?");
        needHelpLabel.setFont(new Font("UniSansBold", Font.BOLD, 15));
        needHelpLabel.setForeground(Color.LIGHT_GRAY);
        needHelpLabel.setBounds(20, 417, 100, 16);

        playButton = new JButton();
        playButton.setText("Play");
        playButton.setForeground(Color.black);
        playButton.setBounds(280, 370, 80, 60);

        highScore = new JLabel();
        highScore.setText("High Score:");
        highScore.setFont(new Font("UniSansBold", Font.BOLD, 20));
        highScore.setForeground(Color.WHITE);
        highScore.setBounds(20, 150, 100, 25);

        highScoreOutput = new JLabel();
        highScoreOutput.setText(DatabaseAdapter.getInstance().printScore());
        DatabaseAdapter.getInstance().addScoreUpdateListener(score -> {
            highScoreOutput.setText(DatabaseAdapter.getInstance().printScore());
        });
        highScoreOutput.setFont(new Font("UniSansBold", Font.BOLD, 20));
        highScoreOutput.setForeground(Color.WHITE);
        highScoreOutput.setBounds(20, 180, 100, 25);

        background = new JLabel("", new ImageIcon("snake.gif"), JLabel.RIGHT);
        background.setBounds(0, 0, 650, 500);

        snake = new JLabel("", new ImageIcon("snake.png"), JLabel.RIGHT);
        snake.setBounds(15,15, 30, 30);

        logOut = new JLabel();
        logOut.setText("Log Out");
        logOut.setFont(new Font("UniSansBold", Font.BOLD, 15));
        logOut.setForeground(Color.LIGHT_GRAY);
        logOut.setBounds(560, 417, 100, 16);


        loginFrame.add(mainTitleLabel);
        loginFrame.add(needHelpLabel);
        loginFrame.add(playButton);
        loginFrame.add(logOut);
        loginFrame.add(highScore);
        loginFrame.add(highScoreOutput);
        loginFrame.add(snake);
        loginFrame.add(background);
        loginFrame.setSize(650, 500);
        loginFrame.setTitle("Sssnake");
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);


        needHelpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object options1 = null;
                int input = JOptionPane.showOptionDialog(null,
                        "For more help, contact me at stilkata@gmail.com",
                        "Need Help?",
                        JOptionPane.OK_OPTION,
                        JOptionPane.CANCEL_OPTION,
                        null,
                        (Object[]) options1,
                        null);
                if (input == JOptionPane.OK_OPTION) {
                    try {
                        Desktop.getDesktop().browse(new URL("http://www.instagram.com/stilka_kostilka?igshid=e3jperojo9af").toURI());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (URISyntaxException uriSyntaxException) {
                        uriSyntaxException.printStackTrace();
                    }
                }
                else
                {

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                needHelpLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                needHelpLabel.setForeground(Color.LIGHT_GRAY);
            }
        });

        logOut.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.dispose();
                new GUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logOut.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logOut.setForeground(Color.LIGHT_GRAY);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameFrame(Login.this.username);
            }
        });
    }
}
