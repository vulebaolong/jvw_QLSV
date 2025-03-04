/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

import javax.swing.SwingUtilities;
import view.MainFrame;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("Không thể đặt giao diện Dark Mode.");
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
