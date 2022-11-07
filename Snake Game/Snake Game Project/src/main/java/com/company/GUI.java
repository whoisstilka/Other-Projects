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

public class GUI extends JFrame {
    JFrame mainFrame = new JFrame(); // Добавяме нов JFrame и неговите компоненти
    JPanel mainPanel = new JPanel();
    JLabel mainTitleLabel;
    JLabel mainLoginLabel;
    JLabel mainUserInputLabel;
    public JTextField mainUserInputTFL;
    JLabel mainPasswordInputLabel;
    JPasswordField mainPasswordInputTFL;
    JLabel background;
    JButton signInButton;
    JLabel noLoginLabel;
    JLabel forgotPasswordLabel;
    JLabel needHelpLabel;
    JLabel snake;


    GUI() { // Конструктор GUI

        mainTitleLabel = new JLabel(); // Създаваме нов JLabel
        mainTitleLabel.setText("Simple Snake Game"); // Задаваме текста в JLabel-а
        mainTitleLabel.setFont(new Font("UniSansBold", Font.BOLD, 40)); // Задаваме шрифта и размера на текста
        mainTitleLabel.setForeground(Color.WHITE); // Задаваме цвета на текста
        mainTitleLabel.setBounds(150, 15, 350, 45); // Задаваме позицията на текста

        mainLoginLabel = new JLabel();
        mainLoginLabel.setText("Login");
        mainLoginLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        mainLoginLabel.setForeground(Color.WHITE);
        mainLoginLabel.setBounds(100, 90, 350, 45);

        mainUserInputLabel = new JLabel();
        mainUserInputLabel.setText("Username:");
        mainUserInputLabel.setFont(new Font("UniSansBold", Font.BOLD, 20));
        mainUserInputLabel.setForeground(Color.WHITE);
        mainUserInputLabel.setBounds(50, 130, 350, 45);

        mainUserInputTFL = new JTextField(); // Създаваме нов JTextField
        mainUserInputTFL.setBackground(Color.WHITE); // Задаваме background цвета на JTextField-a
        mainUserInputTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Задаваме Empty Border опция на JTextField
        mainUserInputTFL.setBounds(50, 165, 160, 30);

        mainPasswordInputLabel = new JLabel();
        mainPasswordInputLabel.setText("Password:");
        mainPasswordInputLabel.setFont(new Font("UniSansBold", Font.BOLD, 20));
        mainPasswordInputLabel.setForeground(Color.WHITE);
        mainPasswordInputLabel.setBounds(50, 200, 350, 45);

        mainPasswordInputTFL = new JPasswordField(); // Създаваме нов JPasswordField
        mainPasswordInputTFL.setBackground(Color.WHITE);
        mainPasswordInputTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mainPasswordInputTFL.setBounds(50, 235, 160, 30);

        signInButton = new JButton(); // Създаваме нов бутон JButton
        signInButton.setText("Sign In"); // Задаваме текста на бутона
        signInButton.setForeground(Color.black); // Задаваме цвета на текста на бутона
        signInButton.setBounds(90, 285, 80, 30);

        noLoginLabel = new JLabel();
        noLoginLabel.setText("Don't have an account? Register Now!");
        noLoginLabel.setFont(new Font("UniSansBold", Font.BOLD, 15));
        noLoginLabel.setForeground(Color.LIGHT_GRAY);
        noLoginLabel.setBounds(20, 367, 235, 16);

        forgotPasswordLabel = new JLabel();
        forgotPasswordLabel.setText("Forgot Password?");
        forgotPasswordLabel.setFont(new Font("UniSansBold", Font.BOLD, 15));
        forgotPasswordLabel.setForeground(Color.LIGHT_GRAY);
        forgotPasswordLabel.setBounds(20, 392, 110, 16);

        needHelpLabel = new JLabel();
        needHelpLabel.setText("Need Help?");
        needHelpLabel.setFont(new Font("UniSansBold", Font.BOLD, 15));
        needHelpLabel.setForeground(Color.LIGHT_GRAY);
        needHelpLabel.setBounds(20, 417, 100, 16);

        background = new JLabel("", new ImageIcon("snake.gif"), JLabel.RIGHT); // Използваме JLabel за да добавим custom background
        background.setBounds(0, 0, 650, 500);

        snake = new JLabel("", new ImageIcon("snake.png"), JLabel.RIGHT);
        snake.setBounds(15,15, 30, 30);

        mainFrame.add(mainTitleLabel); // Добавяме различните компоненти към JFrame-a, за да се виждат
        mainFrame.add(mainLoginLabel);
        mainFrame.add(mainUserInputLabel);
        mainFrame.add(mainUserInputTFL);
        mainFrame.add(mainPasswordInputLabel);
        mainFrame.add(mainPasswordInputTFL);
        mainFrame.add(signInButton);
        mainFrame.add(noLoginLabel);
        mainFrame.add(forgotPasswordLabel);
        mainFrame.add(needHelpLabel);
        mainFrame.add(snake);
        mainFrame.add(background);
        mainFrame.setSize(650, 500); // Задаваме размера на JFrame-a
        mainFrame.setTitle("Sssnake"); // Задаваме Title-а на JFrame-a
        mainFrame.setLocationRelativeTo(null); // Задаваме локацията на JFrame-a да е в центъра на екрана
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Задаваме Default Close Operation
        mainFrame.setVisible(true); // Задаваме видимостта на JFrame-a


        noLoginLabel.addMouseListener(new MouseListener() { // Създаваме нов Mouse Listener
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register(); // Създаваме нов object Register
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                noLoginLabel.setForeground(Color.WHITE); // Когато мишката влезе в box-а на текста цвета на JLabel-а се променя
            }

            @Override
            public void mouseExited(MouseEvent e) {
                noLoginLabel.setForeground(Color.LIGHT_GRAY); // Когато мишката излезе от box-a на текста цвета на JLabel-а се връща към първоначалният
            }
        });

        forgotPasswordLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgotPassword();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forgotPasswordLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotPasswordLabel.setForeground(Color.LIGHT_GRAY);
            }
        });
        
        needHelpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object options1 = null; // Създаваме custom Option Dialog с опции OK и Cancel
                int input = JOptionPane.showOptionDialog(null,
                        "For more help, contact me at stilkata@gmail.com",
                        "Need Help?",
                        JOptionPane.OK_OPTION,
                        JOptionPane.CANCEL_OPTION,
                        null,
                        (Object[]) options1,
                        null);
                if (input == JOptionPane.OK_OPTION) { // Ако потребителя цъкне върху OK бутона се отваря нов URL
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

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DatabaseAdapter.getInstance().checkLogin(mainUserInputTFL.getText(), String.valueOf(mainPasswordInputTFL.getPassword()))) // Получаваме истанция checkLogin и нейните параметри
                {
                    JOptionPane.showMessageDialog(null, "Welcome Back, " + mainUserInputTFL.getText() + "!"); // Създава се Message Dialog
                    mainFrame.dispose(); // Затваря се mainFrame
                    new Login(mainUserInputTFL.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid User/Password");
                }
            }
        });
    }

    public static void main(String s[])
    {
        GUI gui = new GUI(); // Създаваме нов обект GUI
    }
}



