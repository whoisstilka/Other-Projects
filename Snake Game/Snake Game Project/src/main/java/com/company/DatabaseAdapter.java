package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class DatabaseAdapter {
    private static DatabaseAdapter instance; // Създаваме истанция на DatabaseAdapter
    private Connection conn; // Създаваме connection
    private List<Consumer<Integer>> highScoreUpdateListeners = new ArrayList<>();

    private DatabaseAdapter() { // Свързваме се към източника на данни
        String url = "jdbc:mysql://localhost:3306/users";
        String user = "root";
        String password = "771bcd31";
        String score = "0";
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }

    synchronized public static DatabaseAdapter getInstance() { // Създаваме нова истанция
        if (instance == null) {
            instance = new DatabaseAdapter();
        }
        return instance;
    }

    public void createAccount(String user, String password) { // Метод за въвеждане на данни в базата данни
        String sql = "INSERT INTO tbl_user(u_user, u_pass, u_score) VALUES (?, ?, 0)";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);
            pst.execute();
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }

    public boolean checkLogin(String user, String password) { // Метод за проверка на данните за Login
        String sql = "SELECT * FROM tbl_user WHERE u_user = ? AND u_pass = ?";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);
            return pst.executeQuery().next();
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
        return false;
    }

    public void changePassword(String user, String password) { // Метод за промяна на паролата
        String sql = "UPDATE tbl_user SET u_pass = ? WHERE u_user = ?";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, user);
            pst.execute();
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }

    public void updateScore(int score, String user) { // Метод за записване и ъпдейтване на High Score-a на играча
        String sql = "UPDATE tbl_user SET u_score = ? WHERE u_user = ? AND u_score < ?";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, score);
            pst.setString(2, user);
            pst.setInt(3, score);
            pst.execute();
            for (Consumer<Integer> listener : this.highScoreUpdateListeners) {
                listener.accept(score);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }
    }

    public String printScore() { // Метод за print-ване на High Score-a
        String sql = "SELECT u_score, u_user FROM tbl_user ORDER BY u_score DESC LIMIT 1";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                int score = rs.getInt(1);
                String user = rs.getString(2);

                return user + " - " + score;
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
            return "";
        }
        return "";
    }

    public void addScoreUpdateListener(Consumer<Integer> listener) {
        this.highScoreUpdateListeners.add(listener);
    }

    public void removeScoreUpdateListener(Consumer<Integer> listener) {
        this.highScoreUpdateListeners.remove(listener);
    }
}
