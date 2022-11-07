package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword extends JFrame {
    JFrame FPFrame = new JFrame();
    JPanel FPPanel = new JPanel();
    JLabel forgotPassTitle;
    JLabel typeUsernameLabel;
    JTextField typeUsernameTFL;
    JLabel putNewPasswordLabel;
    JPasswordField newForgotPasswordTFL;
    JLabel repeatNewForgotPasswordLabel;
    JPasswordField repeatNewForgotPasswordTFL;
    JButton changePassword;


    ForgotPassword() {
        FPPanel.setBackground(Color.BLACK);

        forgotPassTitle = new JLabel();
        forgotPassTitle.setText("Change your password!");
        forgotPassTitle.setFont(new Font("UniSansBold", Font.BOLD, 40));
        forgotPassTitle.setForeground(Color.WHITE);
        forgotPassTitle.setBounds(50, 15, 450, 45);

        typeUsernameLabel = new JLabel();
        typeUsernameLabel.setText("Username:");
        typeUsernameLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        typeUsernameLabel.setForeground(Color.WHITE);
        typeUsernameLabel.setBounds(180, 100, 350, 45);

        typeUsernameTFL = new JTextField();
        typeUsernameTFL.setBackground(Color.WHITE);
        typeUsernameTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        typeUsernameTFL.setBounds(155, 140, 160, 30);

        putNewPasswordLabel = new JLabel();
        putNewPasswordLabel.setText("New Password:");
        putNewPasswordLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        putNewPasswordLabel.setForeground(Color.WHITE);
        putNewPasswordLabel.setBounds(155, 180, 350, 45);

        newForgotPasswordTFL = new JPasswordField();
        newForgotPasswordTFL.setBackground(Color.WHITE);
        newForgotPasswordTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        newForgotPasswordTFL.setBounds(155, 220, 160, 30);

        repeatNewForgotPasswordLabel = new JLabel();
        repeatNewForgotPasswordLabel.setText("Repeat New Password:");
        repeatNewForgotPasswordLabel.setFont(new Font("UniSansBold", Font.BOLD, 25));
        repeatNewForgotPasswordLabel.setForeground(Color.WHITE);
        repeatNewForgotPasswordLabel.setBounds(115, 260, 350, 45);

        repeatNewForgotPasswordTFL = new JPasswordField();
        repeatNewForgotPasswordTFL.setBackground(Color.WHITE);
        repeatNewForgotPasswordTFL.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        repeatNewForgotPasswordTFL.setBounds(155, 300, 160, 30);

        changePassword = new JButton();
        changePassword.setText("Change Password");
        changePassword.setForeground(Color.black);
        changePassword.setBounds(160, 355, 150, 30);


        FPFrame.add(typeUsernameLabel);
        FPFrame.add(forgotPassTitle);
        FPFrame.add(typeUsernameLabel);
        FPFrame.add(typeUsernameTFL);
        FPFrame.add(putNewPasswordLabel);
        FPFrame.add(newForgotPasswordTFL);
        FPFrame.add(repeatNewForgotPasswordLabel);
        FPFrame.add(repeatNewForgotPasswordTFL);
        FPFrame.add(changePassword);
        FPFrame.add(FPPanel);
        FPFrame.setSize(500, 500);
        FPFrame.setTitle("Register");
        FPFrame.setLocationRelativeTo(null);
        FPFrame.setVisible(true);

        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newForgotPasswordTFL.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please, fill in the blank spaces.");
                }
                else if(repeatNewForgotPasswordTFL.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please, fill in the blank spaces.");
                }
                else if(newForgotPasswordTFL.getText().equals(repeatNewForgotPasswordTFL.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Password changed.");
                    FPFrame.dispose();
                    DatabaseAdapter.getInstance().changePassword(typeUsernameTFL.getText(), String.valueOf(repeatNewForgotPasswordTFL.getPassword()));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Passwords don't match.");
                    newForgotPasswordTFL.setText("");
                    repeatNewForgotPasswordTFL.setText("");
                }
            }
        });
    }
}