/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.CardLayout;
import model.User;
import session.UserSession;
import javax.swing.*;
import java.awt.*;
import components.Toast;

 
public class MainFrame extends javax.swing.JFrame {

    private CardLayout clientCardLayout;
    private CardLayout authCardLayout;
    private JPanel contentPanel;
    private JPanel authPanel;
    private JPanel clientPanel;

    public AdminPanel adminPanel;
    public TeacherPanel teacherPanel;
    public StudentPanel studentPanel;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initCustomComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));

        mainPanel.setAlignmentX(0.0F);
        mainPanel.setAlignmentY(0.0F);
        mainPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame().setVisible(true);
//            }
//        });
    }

    private void initCustomComponents() {
        int heightHeader = 70;
        int heightContent = 500;
        int heightApp = heightHeader + heightContent;
        int widthApp = 1000;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý sinh viên");
        setSize(widthApp, heightApp);
        setLayout(new CardLayout()); // Sử dụng CardLayout cho JFrame

        // 🔹 **1. Layout Client (Có Header)** --------------------------------
        clientPanel = new JPanel(new BorderLayout());

        // 👉 Header
        HeaderPanel headerPanel = new HeaderPanel(this);
        headerPanel.setPreferredSize(new Dimension(widthApp, heightHeader));
        clientPanel.add(headerPanel, BorderLayout.NORTH);

        // 👉 Content Panel
        contentPanel = new JPanel();
        clientCardLayout = new CardLayout(); // 👉 Thêm CardLayout riêng cho Client
        contentPanel.setLayout(clientCardLayout);
        contentPanel.setPreferredSize(new Dimension(widthApp, heightContent));
        // 👉 Các màn hình trong clientPanel
        adminPanel = new AdminPanel();
        teacherPanel = new TeacherPanel();
        studentPanel = new StudentPanel();
        contentPanel.add(adminPanel, "Admin");
        contentPanel.add(teacherPanel, "Teacher");
        contentPanel.add(studentPanel, "Student");
        clientPanel.add(contentPanel, BorderLayout.CENTER);
        // ----------------------------------------------------------------------

        // 🔹 **2. Layout Auth (Full màn hình)** --------------------------------
        authPanel = new JPanel();
        authCardLayout = new CardLayout(); // 👉 Thêm CardLayout riêng cho Auth
        authPanel.setLayout(authCardLayout);
        authPanel.setPreferredSize(new Dimension(widthApp, heightApp));

        // 👉 Login & Register Panel (Không có header)
        LoginPanel loginPanel = new LoginPanel(this, headerPanel, studentPanel);
        RegisterPanel registerPanel = new RegisterPanel(this);
        authPanel.add(loginPanel, "Login");
        authPanel.add(registerPanel, "Register");
        // ----------------------------------------------------------------------

        // 🔹 Thêm vào JFrame (Dùng CardLayout để chuyển đổi giữa Auth & Client)
        add(authPanel, "AuthLayout");
        add(clientPanel, "ClientLayout");

        // 🔹 Xác định màn hình khi mở ứng dụng
        UserSession.printAllPreferences();
        User currentUser = UserSession.getInfo();
        showLayout(currentUser != null ? "ClientLayout" : "AuthLayout");

        if (currentUser != null) {
            switch (currentUser.getRole()) {
                case "Admin":
                    showPanelClient("Admin");
                    break;
                case "Teacher":
                    showPanelClient("Teacher");
                    break;
                default:
                    showPanelClient("Student");
                    break;
            }
        } else {
            showPanelAuth("Login");
        }

        setLocationRelativeTo(null);
        setVisible(true);
        Toast.init(this, 2000, Toast.Position.BOTTOM_RIGHT);
    }

    public void showPanelClient(String panelName) {
        showLayout("ClientLayout");
        clientCardLayout.show(contentPanel, panelName);
    }

    public void showPanelAuth(String panelName) {
        showLayout("AuthLayout");
        authCardLayout.show(authPanel, panelName);
    }

    public void showLayout(String layoutName) {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), layoutName);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
