/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.UserDAO;
import session.UserSession;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import components.Toast;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        restoreRememberMeState();
        listenEnter();
    }

    private void listenEnter() {
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });

        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });
    }

    private void restoreRememberMeState() {
        boolean isChecked = UserSession.getRememberMeState();
        cbRemember.setSelected(isChecked);
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
        cbRemember = new javax.swing.JCheckBox();

        lbPassword.setText("Password");

        txtPassword.setText("123123");
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");

        lbEmail.setText("Email:");

        txtEmail.setText("admin@gmail.com");

        lbRegister.setText("Register");
        lbRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegisterMouseClicked(evt);
            }
        });

        cbRemember.setText("Remember Me");
        cbRemember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRememberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPassword)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbRemember))
                            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(370, 370, 370))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRemember)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(10, 10, 10)
                .addComponent(lbRegister)
                .addContainerGap(209, Short.MAX_VALUE))
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

        boolean rememberMeChecked = cbRemember.isSelected();
        UserSession.saveRememberMeState(rememberMeChecked);

        if (rememberMeChecked) {
            UserSession.saveUserId(userExists.getId());
        } else {
            UserSession.logout();
        }

        this.headerPanel.reloadDataHeader();
        this.studentPanel.reloadDataStudent();

        mainFrame.showLayout("ClientLayout");

        switch (role) {
            case "Admin":
                mainFrame.adminPanel.reloadDataAdmin();
                mainFrame.showPanelClient("Admin");
                break;
            case "Teacher":
                mainFrame.teacherPanel.reloadDataTeacher();
                mainFrame.showPanelClient("Teacher");
                break;
            default:
                mainFrame.studentPanel.reloadDataStudent();
                mainFrame.showPanelClient("Student");
                break;
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lbRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegisterMouseClicked
        mainFrame.showPanelAuth("Register");
    }//GEN-LAST:event_lbRegisterMouseClicked

    private void cbRememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRememberActionPerformed
        boolean isChecked = cbRemember.isSelected();
        UserSession.saveRememberMeState(isChecked);
    }//GEN-LAST:event_cbRememberActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox cbRemember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbRegister;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
