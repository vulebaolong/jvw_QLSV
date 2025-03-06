/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.ClassesDAO;
import dao.SubjectsDAO;
import dao.StudentDAO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.Classes;
import model.Student;
import session.UserSession;
import model.User;
import view.dialog.AddStudentDialog;
import view.dialog.RegisterStudentDialog;

/**
 *
 * @author vulebaolong
 */
public class StudentPanel extends javax.swing.JPanel {

    private ClassesDAO classesDAO;
    private SubjectsDAO subjectsDAO;
    private StudentDAO studentDAO;

    /**
     * Creates new form StudentPanel
     */
    public StudentPanel() {
        initComponents();
        classesDAO = new ClassesDAO();
        subjectsDAO = new SubjectsDAO();
        loadInfoUserData();
    }

    public void loadInfoUserData() {
        User user = UserSession.getInfo();
        Student student = user.getStudent();
        panelInfoStudent.setPreferredSize(new Dimension(572, 262));

        if (user == null || student == null) {
            jButton1.setVisible(true);
            lbFullname.setVisible(false);
            lbBirthDay.setVisible(false);
            lbYear.setVisible(false);
            lbClass.setVisible(false);
            lbDepartment.setVisible(false);

            lb1.setVisible(false);
            lb2.setVisible(false);
            lb3.setVisible(false);
            lb4.setVisible(false);
            lb5.setVisible(false);
            lb6.setVisible(false);
            lb7.setVisible(false);
            lb8.setVisible(false);
            lb9.setVisible(false);
            lb10.setVisible(false);

        } else {
            jButton1.setVisible(false);
            lbFullname.setVisible(true);
            lbBirthDay.setVisible(true);
            lbYear.setVisible(true);
            lbClass.setVisible(true);
            lbDepartment.setVisible(true);

            lb1.setVisible(true);
            lb2.setVisible(true);
            lb3.setVisible(true);
            lb4.setVisible(true);
            lb5.setVisible(true);
            lb6.setVisible(true);
            lb7.setVisible(true);
            lb8.setVisible(true);
            lb9.setVisible(true);
            lb10.setVisible(true);

            lbFullname.setText(student.getFullName());
            lbBirthDay.setText(student.getBirthDay().toString());

            Timestamp createdAt = student.getCreatedAt();
            if (createdAt != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                lbYear.setText(sdf.format(new Date(createdAt.getTime())));
            } else {
                lbYear.setText("N/A");
            }

            Classes classes = student.getClasses();
            if (classes != null) {
                lbClass.setText(classes.getClassName());
                lbDepartment.setText(classes.getDepartment());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListSubjects = new javax.swing.JTable();
        panelInfoStudent = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lbFullname = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lbBirthDay = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        lbDepartment = new javax.swing.JLabel();
        lb7 = new javax.swing.JLabel();
        lb8 = new javax.swing.JLabel();
        lbYear = new javax.swing.JLabel();
        lb9 = new javax.swing.JLabel();
        lb10 = new javax.swing.JLabel();
        lbClass = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 500));
        setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel2.setText("Danh Sách Môn Học");

        tbListSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên Môn Học", "Tín Chỉ"
            }
        ));
        jScrollPane2.setViewportView(tbListSubjects);

        panelInfoStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lb1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb1.setText("Họ tên/");

        lb2.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb2.setText("Fullname:");

        lbFullname.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lbFullname.setText("------------------------");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel5.setText("Sinh Viên");

        lb3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb3.setText("Ngày sinh/");

        lb4.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb4.setText("DoB:");

        lbBirthDay.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lbBirthDay.setText("-----------------------------------");

        lb5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb5.setText("Ngành học/");

        lb6.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb6.setText("Major:");

        lbDepartment.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lbDepartment.setText("-----------------------------------");

        lb7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb7.setText("Năm nhập học/");

        lb8.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb8.setText("Year of admission:");

        lbYear.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lbYear.setText("-----------------------------------");

        lb9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb9.setText("Lớp/");

        lb10.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb10.setText("Class:");

        lbClass.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lbClass.setText("---");

        jButton1.setText("Đăng ký sinh viên");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoStudentLayout = new javax.swing.GroupLayout(panelInfoStudent);
        panelInfoStudent.setLayout(panelInfoStudentLayout);
        panelInfoStudentLayout.setHorizontalGroup(
            panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoStudentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelInfoStudentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb8)
                    .addComponent(lb6)
                    .addComponent(lb4)
                    .addGroup(panelInfoStudentLayout.createSequentialGroup()
                        .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb2)
                            .addGroup(panelInfoStudentLayout.createSequentialGroup()
                                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb1)
                                    .addComponent(lb3)
                                    .addComponent(lb5)
                                    .addComponent(lb7))
                                .addGap(32, 32, 32)
                                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbBirthDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb10)
                            .addGroup(panelInfoStudentLayout.createSequentialGroup()
                                .addComponent(lb9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
            .addGroup(panelInfoStudentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoStudentLayout.setVerticalGroup(
            panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoStudentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoStudentLayout.createSequentialGroup()
                        .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb1)
                            .addComponent(lbFullname))
                        .addGap(0, 0, 0)
                        .addComponent(lb2))
                    .addGroup(panelInfoStudentLayout.createSequentialGroup()
                        .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb9)
                            .addComponent(lbClass))
                        .addGap(0, 0, 0)
                        .addComponent(lb10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb3)
                    .addComponent(lbBirthDay))
                .addGap(0, 0, 0)
                .addComponent(lb4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb5)
                    .addComponent(lbDepartment))
                .addGap(0, 0, 0)
                .addComponent(lb6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb7)
                    .addComponent(lbYear))
                .addGap(0, 0, 0)
                .addComponent(lb8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(panelInfoStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 416, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RegisterStudentDialog addDialog = new RegisterStudentDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, this);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb10;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JLabel lb9;
    private javax.swing.JLabel lbBirthDay;
    private javax.swing.JLabel lbClass;
    private javax.swing.JLabel lbDepartment;
    private javax.swing.JLabel lbFullname;
    private javax.swing.JLabel lbYear;
    private javax.swing.JPanel panelInfoStudent;
    private javax.swing.JTable tbListSubjects;
    // End of variables declaration//GEN-END:variables
}
