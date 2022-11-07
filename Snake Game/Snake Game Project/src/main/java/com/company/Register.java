package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    JFrame registerFrame = new JFrame();
    JPanel registerPanel = new JPanel();
    JLabel createNewAccTitle;
    JLabel createNewUsernameLabel;
    JTextField newUsernameTFL;
    JLabel createNewPasswordLabel;
    JPasswordField newPasswordTFL;
    JLabel repeatNewPasswordLabel;
    JPasswordField repeatNewPasswordTFL;
    JButton signUpButton;

    Register() {
        registerPanel.setBackground(Color.BLACK); // Задаваме background color на JPanel

        createNewAccTitle = new JLabel();
        createNewAccTitle.setText("Create your new account!");
        createNewAccTitle.setFont(new Font("UniSansBold", Font.BOLD, 40));
        createNewAccTitle.setForeground(Color.WHITE);
        createNewAccTitle.setBounds(30, 15, 450, 45);

        createNewUsernameLabel = new JLabel();
        createNewUsernameLabel.setText("Username:");
        createNewUsernameLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        createNewUsernameLabel.setForeground(Color.WHITE);
        createNewUsernameLabel.setBounds(180, 100, 350, 45);

        newUsernameTFL = new JTextField();
        newUsernameTFL.setBackground(Color.WHITE);
        newUsernameTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        newUsernameTFL.setBounds(155, 140, 160, 30);

        createNewPasswordLabel = new JLabel();
        createNewPasswordLabel.setText("Password:");
        createNewPasswordLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        createNewPasswordLabel.setForeground(Color.WHITE);
        createNewPasswordLabel.setBounds(180, 180, 350, 45);

        newPasswordTFL = new JPasswordField();
        newPasswordTFL.setBackground(Color.WHITE);
        newPasswordTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        newPasswordTFL.setBounds(155, 220, 160, 30);

        repeatNewPasswordLabel = new JLabel();
        repeatNewPasswordLabel.setText("Repeat New Password:");
        repeatNewPasswordLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        repeatNewPasswordLabel.setForeground(Color.WHITE);
        repeatNewPasswordLabel.setBounds(115, 260, 350, 45);

        repeatNewPasswordTFL = new JPasswordField();
        repeatNewPasswordTFL.setBackground(Color.WHITE);
        repeatNewPasswordTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        repeatNewPasswordTFL.setBounds(155, 300, 160, 30);

        signUpButton = new JButton();
        signUpButton.setText("Sign Up");
        signUpButton.setForeground(Color.black);
        signUpButton.setBounds(195, 355, 80, 30);


        registerFrame.add(createNewAccTitle);
        registerFrame.add(createNewUsernameLabel);
        registerFrame.add(newUsernameTFL);
        registerFrame.add(createNewPasswordLabel);
        registerFrame.add(newPasswordTFL);
        registerFrame.add(repeatNewPasswordLabel);
        registerFrame.add(repeatNewPasswordTFL);
        registerFrame.add(signUpButton);
        registerFrame.add(registerPanel);
        registerFrame.setSize(500, 500);
        registerFrame.setTitle("Register");
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(true);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newUsernameTFL.getText().equals("")) // Ако username-а (взимаме input-а от JTextField-a) е равен на нищо
                {
                    JOptionPane.showMessageDialog(null, "Please, fill in the blank spaces."); // Създаваме message Dialog
                }
                else if(newPasswordTFL.getText().equals(repeatNewPasswordTFL.getText())) // Ако въведените нови пароли са еднакви
                {
                    JOptionPane.showMessageDialog(null, "Sign Up Complete!");
                    registerFrame.dispose();
                    DatabaseAdapter.getInstance().createAccount(newUsernameTFL.getText(), String.valueOf(newPasswordTFL.getPassword()));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Passwords don't match.");
                    newPasswordTFL.setText(""); // Задаваме обратно съдържанието на TextField-a на празно поле
                    repeatNewPasswordTFL.setText("");
                }
            }
        });
    }
}
