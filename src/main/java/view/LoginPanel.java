/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.UserDAO;
import session.UserSession;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import components.Toast;

/**
 *
 * @author vulebaolong
 */
public class LoginPanel extends javax.swing.JPanel {

    private MainFrame mainFrame;
    private UserDAO userDao;
    private HeaderPanel headerPanel;
    private StudentPanel studentPanel;

    /**
     * Creates new form LoginPanel
     */
    public LoginPanel(MainFrame frame, HeaderPanel headerPanel, StudentPanel studentPanel) {
        this.mainFrame = frame;
        this.headerPanel = headerPanel;
        this.studentPanel = studentPanel;

        this.userDao = new UserDAO();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbRegister = new javax.swing.JLabel();

        lbPassword.setText("Password");

        txtPassword.setText("123123");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");

        lbEmail.setText("Email:");

        txtEmail.setText("long@gmail.com");

        lbRegister.setText("Register");
        lbRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegisterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(283, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtEmail))))
                .addContainerGap(283, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnLogin)
                .addGap(18, 18, 18)
                .addComponent(lbRegister)
                .addContainerGap(196, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty()) {
            Toast.show("❌ Email không được để trống!");
            txtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Toast.show("❌ Mật khẩu không được để trống!");
            txtPassword.requestFocus();
            return;
        }

        System.out.println("email: " + email);
        System.out.println("password: " + password);

        User userExists = userDao.getUserByEmail(email);

        if (userExists == null) {
            Toast.show("❌ Email không tồn tại");
            txtEmail.requestFocus();
            return;
        }

        String hashedPassword = userExists.getPassword();

        boolean isPassword = BCrypt.checkpw(password, hashedPassword);

        if (!isPassword) {
            Toast.show("❌ Mật khẩu không chính xác");
            txtPassword.requestFocus();
            return;
        }

        Toast.show("✅ Đăng nhập thành công!");

        String role = userExists.getRole();

        UserSession.saveUserId(userExists.getId());

        this.headerPanel.loadDataHeader();
        this.studentPanel.loadInfoUserData();

        mainFrame.showLayout("ClientLayout");

        switch (role) {
            case "Admin":
                mainFrame.showPanelClient("Admin");
                break;
            case "Teacher":
                mainFrame.showPanelClient("Teacher");
                break;
            default:
                mainFrame.showPanelClient("Student");
                break;
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lbRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegisterMouseClicked
        mainFrame.showPanelAuth("Register");
    }//GEN-LAST:event_lbRegisterMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbRegister;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
