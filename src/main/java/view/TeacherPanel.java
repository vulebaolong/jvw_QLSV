/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import components.DeleteButtonColumn;
import components.Toast;
import dao.SubjectsDAO;
import dao.TeacherDAO;
import dao.TeacherSubjectDAO;
import java.awt.Dimension;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Subject;
import model.Teacher;
import model.TeacherSubject;
import model.User;
import session.UserSession;

 
public class TeacherPanel extends javax.swing.JPanel {

    private Teacher teacher;
    private TeacherSubjectDAO teacherSubjectDAO;
    private SubjectsDAO subjectsDAO;
    private TeacherDAO teacherDAO;

    /**
     * Creates new form TeacherPanel
     */
    public TeacherPanel() {
        initComponents();
        this.teacherSubjectDAO = new TeacherSubjectDAO();
        this.subjectsDAO = new SubjectsDAO();
        this.teacherDAO = new TeacherDAO();
        reloadDataTeacher();
    }

    public void reloadDataTeacher() {
        loadInfoTeacherData();
        loadTeachingClasses();
        loadSubjectData();
    }

    public void loadInfoTeacherData() {
        panelInfoTeacher.setPreferredSize(new Dimension(450, 262));

        User user = UserSession.getInfo();

        if (user != null) {
            teacher = user.getTeacher();
            System.out.println("teacher: " + teacher);

            if (teacher != null) {
                lbFullname.setText(teacher.getFullName());
                lbBirthDay.setText(teacher.getBirthDay().toString());
                lbGender.setText(teacher.getGender());
                lbAddress.setText(teacher.getAddress());
            } else {
                lbFullname.setText("---");
                lbBirthDay.setText("---");
                lbGender.setText("---");
                lbAddress.setText("---");
            }
        }

    }

    private void loadTeachingClasses() {
        DefaultTableModel model = (DefaultTableModel) tbTeachingClasses.getModel();
        model.setRowCount(0);

        User user = UserSession.getInfo();
        if (user != null) {
            Teacher teacher = user.getTeacher();
            if (teacher == null) {
                return;
            }
        } else {
            return;
        }

        int teacherId = user.getTeacher().getId();

        List<TeacherSubject> teacherSubjects = teacherSubjectDAO.getClassesByTeacherId(teacherId);

        // Thêm dữ liệu mới vào bảng
        for (TeacherSubject ts : teacherSubjects) {
            model.addRow(new Object[]{
                ts.getId(),
                ts.getSubject().getSubjectName(),
                ts.getSubject().getCredit(),
                ts.getCreatedAt(),
                ts.getUpdatedAt()
            });
        }

        DeleteButtonColumn.addDeleteButton("Xoá", "Bạn có chắc muốn xóa dòng này?", tbTeachingClasses, 3, (row) -> {
            if (tbTeachingClasses.isEditing()) {
                tbTeachingClasses.getCellEditor().stopCellEditing();
            }

            int teacherSubjectId = (int) model.getValueAt(row, 0);
            boolean success = teacherSubjectDAO.deleteTeacherSubject(teacherSubjectId);

            if (success) {
                reloadDataTeacher();
                Toast.show("✅ Xóa môn dạy thành công!");
            } else {
                Toast.show("❌ Xóa môn dạy thất bại!");
            }
        });
    }

    private void loadSubjectData() {
        DefaultTableModel model = (DefaultTableModel) tableListSubject.getModel();
        model.setRowCount(0);

        User user = UserSession.getInfo();
        if (user != null) {
            Teacher teacher = user.getTeacher();
            if (teacher == null) {
                return;
            }
        } else {
            return;
        }

        int teacherId = user.getTeacher().getId();

        List<Subject> subject = subjectsDAO.getUnregisteredSubjectsForTeacher(teacherId);

        for (Subject s : subject) {
            model.addRow(new Object[]{
                s.getId(),
                s.getSubjectName(),
                s.getCredit()
            });
        }

        DeleteButtonColumn.addDeleteButton("Đăng ký", "Bạn có chắc muốn đăng ký?", tableListSubject, 3, (row) -> {
            if (tableListSubject.isEditing()) {
                tableListSubject.getCellEditor().stopCellEditing();
            }

            int subjectId = (int) model.getValueAt(row, 0);

            // Lấy thông tin giảng viên đang đăng nhập
            if (user == null || user.getTeacher() == null) {
                Toast.show("❌ Không thể đăng ký! Bạn không phải là giảng viên.");
                return;
            }

            // Kiểm tra xem giảng viên đã đăng ký môn này chưa
            if (teacherDAO.isTeaching(teacherId, subjectId)) {
                Toast.show("⚠ Bạn đã đăng ký dạy môn này rồi!");
                return;
            }

            // Tạo đối tượng đăng ký
            TeacherSubject teacherSubject = new TeacherSubject(teacherId, subjectId);

            boolean success = teacherSubjectDAO.addTeacherSubject(teacherSubject);

            if (success) {
                reloadDataTeacher();
                Toast.show("✅ Đăng ký môn dạy thành công!");
            } else {
                Toast.show("❌ Đăng ký môn dạy thất bại!");
            }
        });

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfoTeacher = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lbFullname = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lbBirthDay = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        lbGender = new javax.swing.JLabel();
        lb7 = new javax.swing.JLabel();
        lb8 = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTeachingClasses = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableListSubject = new javax.swing.JTable();

        panelInfoTeacher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lb1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb1.setText("Họ tên/");

        lb2.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb2.setText("Fullname:");

        lbFullname.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lbFullname.setText("------------------------");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel5.setText("Giáo Viên");

        lb3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb3.setText("Ngày sinh/");

        lb4.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb4.setText("DoB:");

        lbBirthDay.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lbBirthDay.setText("-----------------------------------");

        lb5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb5.setText("Giới tính/");

        lb6.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb6.setText("Gender:");

        lbGender.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lbGender.setText("-----------------------------------");

        lb7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lb7.setText("Địa chỉ/");

        lb8.setFont(new java.awt.Font("Helvetica Neue", 2, 10)); // NOI18N
        lb8.setText("Address:");

        lbAddress.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lbAddress.setText("-----------------------------------");

        javax.swing.GroupLayout panelInfoTeacherLayout = new javax.swing.GroupLayout(panelInfoTeacher);
        panelInfoTeacher.setLayout(panelInfoTeacherLayout);
        panelInfoTeacherLayout.setHorizontalGroup(
            panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoTeacherLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb8)
                    .addComponent(lb6)
                    .addComponent(lb4)
                    .addComponent(lb2)
                    .addGroup(panelInfoTeacherLayout.createSequentialGroup()
                        .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb1)
                            .addComponent(lb3)
                            .addComponent(lb5)
                            .addComponent(lb7))
                        .addGap(32, 32, 32)
                        .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbBirthDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoTeacherLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoTeacherLayout.setVerticalGroup(
            panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoTeacherLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb1)
                    .addComponent(lbFullname))
                .addGap(0, 0, 0)
                .addComponent(lb2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb3)
                    .addComponent(lbBirthDay))
                .addGap(2, 2, 2)
                .addComponent(lb4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb5)
                    .addComponent(lbGender))
                .addGap(0, 0, 0)
                .addComponent(lb6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb7)
                    .addComponent(lbAddress))
                .addGap(0, 0, 0)
                .addComponent(lb8)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tbTeachingClasses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên Môn Học", "Tín Chỉ", "Action"
            }
        ));
        jScrollPane1.setViewportView(tbTeachingClasses);

        jLabel1.setText("Môn Học Đang Dạy");

        jLabel2.setText("Danh Sách Môn Học");

        tableListSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên Môn Học", "Tín Chỉ", "Action"
            }
        ));
        jScrollPane2.setViewportView(tableListSubject);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelInfoTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(panelInfoTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbBirthDay;
    private javax.swing.JLabel lbFullname;
    private javax.swing.JLabel lbGender;
    private javax.swing.JPanel panelInfoTeacher;
    private javax.swing.JTable tableListSubject;
    private javax.swing.JTable tbTeachingClasses;
    // End of variables declaration//GEN-END:variables
}
