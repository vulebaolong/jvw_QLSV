package view;

import components.ActionButtonColumn;
import view.dialog.AddStudentDialog;
import view.dialog.ChangeClassDialog;
import components.DeleteButtonColumn;
import components.Toast;
import dao.StudentDAO;
import dao.ClassesDAO;
import dao.SubjectsDAO;
import dao.TeacherDAO;
import model.Student;
import model.Classes;
import model.Subject;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.Teacher;
import view.dialog.AddClassDialog;
import view.dialog.AddSubjectDialog;
import view.dialog.AddTeacherDialog;

public class AdminPanel extends javax.swing.JPanel {

    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private ClassesDAO classesDAO;
    private SubjectsDAO subjectsDAO;

    public AdminPanel() {
        initComponents();
        studentDAO = new StudentDAO();
        classesDAO = new ClassesDAO();
        subjectsDAO = new SubjectsDAO();
        teacherDAO = new TeacherDAO();
        reloadDataAdmin();
    }

    public void reloadDataAdmin() {
        loadStudentData();
        loadClassesData();
        loadSubjectData();
        loadTeacherData();
    }

    private void loadStudentData() {
        DefaultTableModel model = (DefaultTableModel) tbListStudent.getModel();
        model.setRowCount(0);

        tbListStudent.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbListStudent.getColumnModel().getColumn(6).setPreferredWidth(100);
        tbListStudent.getColumnModel().getColumn(7).setPreferredWidth(150);

        List<Student> students = studentDAO.getAllStudents();

        System.out.println("students: " + students);

        for (Student s : students) {
            model.addRow(new Object[]{
                s.getId(),
                s.getFullName(),
                s.getBirthDay(),
                s.getGender(),
                s.getPhone(),
                s.getAddress(),
                s.getClassId() == 0 ? "chưa có lớp" : s.getClassId()
            });
        }

        tbListStudent.getColumnModel().getColumn(7).setCellRenderer(new ActionButtonColumn(tbListStudent, this::deleteStudent, this::showChangeClassPopup));
        tbListStudent.getColumnModel().getColumn(7).setCellEditor(new ActionButtonColumn(tbListStudent, this::deleteStudent, this::showChangeClassPopup));
    }

    private void loadClassesData() {
        DefaultTableModel model = (DefaultTableModel) tableListClass.getModel();
        model.setRowCount(0);

        List<Classes> classes = classesDAO.getAllClasses();

        for (Classes s : classes) {
            model.addRow(new Object[]{
                s.getId(),
                s.getClassName(),
                s.getDepartment()
            });
        }

        DeleteButtonColumn.addDeleteButton("Xoá", "Bạn có chắc muốn xóa dòng này?", tableListClass, 3, (row) -> {
            if (tableListClass.isEditing()) {
                tableListClass.getCellEditor().stopCellEditing();
            }

            int studentId = (int) model.getValueAt(row, 0);
            boolean success = classesDAO.deleteClassById(studentId);

            if (success) {
                reloadDataAdmin();
                Toast.show("✅ Xóa lớp thành công!");
            } else {
                Toast.show("❌ Xóa lớp thất bại!");
            }
        });
    }

    private void loadSubjectData() {
        DefaultTableModel model = (DefaultTableModel) tableListSubject.getModel();
        model.setRowCount(0);

        List<Subject> subject = subjectsDAO.getAllSubjects();

        for (Subject s : subject) {
            model.addRow(new Object[]{
                s.getId(),
                s.getSubjectName(),
                s.getCredit()
            });
        }

        DeleteButtonColumn.addDeleteButton("Xoá", "Bạn có chắc muốn xóa dòng này?", tableListSubject, 3, (row) -> {
            if (tableListSubject.isEditing()) {
                tableListSubject.getCellEditor().stopCellEditing();
            }

            int id = (int) model.getValueAt(row, 0);
            boolean success = subjectsDAO.deleteSubject(id);

            if (success) {
                reloadDataAdmin();
                Toast.show("✅ Xóa môn học thành công!");
            } else {
                Toast.show("❌ Xóa môn học thất bại!");
            }
        });
    }

    private void loadTeacherData() {
        DefaultTableModel model = (DefaultTableModel) tableListTeacher.getModel();
        model.setRowCount(0);

        List<Teacher> teachers = teacherDAO.getAllTeacher();

        for (Teacher s : teachers) {
            model.addRow(new Object[]{
                s.getId(),
                s.getFullName(),
                s.getBirthDay(),
                s.getGender(),
                s.getPhone(),
                s.getAddress()
            });
        }

        DeleteButtonColumn.addDeleteButton("Xoá", "Bạn có chắc muốn xóa dòng này?", tableListTeacher, 6, (row) -> {
            if (tableListTeacher.isEditing()) {
                tableListTeacher.getCellEditor().stopCellEditing();
            }

            int teacherId = (int) model.getValueAt(row, 0);
            boolean success = teacherDAO.deleteTeacherByIdWidthTransaction(teacherId);

            if (success) {
                reloadDataAdmin();
                Toast.show("✅ Xóa giáo viên thành công!");
            } else {
                Toast.show("❌ Xóa giáo viên thất bại!");
            }
        });
    }

    // Callback khi nhấn nút "Xóa"
    private void deleteStudent(int row) {
        DefaultTableModel model = (DefaultTableModel) tbListStudent.getModel();
        if (tbListStudent.isEditing()) {
            tbListStudent.getCellEditor().stopCellEditing();
        }
        if (row >= model.getRowCount()) {
            Toast.show("❌ Lỗi: Hàng cần xóa không tồn tại!");
            return;
        }

        int studentId = (int) model.getValueAt(row, 0);

        boolean success = studentDAO.deleteStudentById(studentId);
        if (success) {
            reloadDataAdmin();
            Toast.show("✅ Xóa sinh viên thành công!");
        } else {
            Toast.show("❌ Xóa sinh viên thất bại!");
        }
    }

    private void showChangeClassPopup(int row) {
        DefaultTableModel model = (DefaultTableModel) tbListStudent.getModel();

        int studentId = (int) model.getValueAt(row, 0);
        String fullName = (String) model.getValueAt(row, 1);

        Object birthDayObj = model.getValueAt(row, 2);
        Date birthDay;

        if (birthDayObj instanceof Date) {
            birthDay = (Date) birthDayObj;
        } else {
            birthDay = Date.valueOf(birthDayObj.toString());
        }

        String gender = (String) model.getValueAt(row, 3);
        String phone = (String) model.getValueAt(row, 4);
        String address = (String) model.getValueAt(row, 5);
        Object classIdObj = model.getValueAt(row, 6);

        int classId;
        if (classIdObj instanceof Integer) {
            classId = (int) classIdObj;
        } else {
            classId = 0;
        }

        Student student = new Student(studentId, fullName, birthDay, gender, phone, address, classId);

        ChangeClassDialog changeClassDialog = new ChangeClassDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, student, this);
        changeClassDialog.setLocationRelativeTo(this);
        changeClassDialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCreateStudent = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListStudent = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnCreateClasses = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableListClass = new javax.swing.JTable();
        lbListSubject = new javax.swing.JLabel();
        btnCreateSubject = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableListSubject = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnCreateTeacher = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableListTeacher = new javax.swing.JTable();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 500));

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 1000));

        jLabel1.setText("Danh Sách Sinh Viên");

        btnCreateStudent.setText("Tạo");
        btnCreateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateStudentActionPerformed(evt);
            }
        });

        tbListStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "Ngày Sinh", "Giới Tính", "Phone", "Địa Chỉ", "ID Lớp", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbListStudent.setColumnSelectionAllowed(true);
        tbListStudent.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbListStudentPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tbListStudent);

        jLabel2.setText("Danh Sách Lớp");

        btnCreateClasses.setText("Tạo");
        btnCreateClasses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClassesActionPerformed(evt);
            }
        });

        tableListClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên Lớp", "Ngành", "Action"
            }
        ));
        tableListClass.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableListClassPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(tableListClass);

        lbListSubject.setText("Danh Sách Môn Học");

        btnCreateSubject.setText("Tạo");
        btnCreateSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSubjectActionPerformed(evt);
            }
        });

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableListSubject);

        jLabel3.setText("Danh Sách Giáo Viên");

        btnCreateTeacher.setText("Tạo");
        btnCreateTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTeacherActionPerformed(evt);
            }
        });

        tableListTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Địa Chỉ", "Action"
            }
        ));
        jScrollPane5.setViewportView(tableListTeacher);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateStudent)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCreateTeacher)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbListSubject)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCreateSubject))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCreateClasses))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(621, 621, 621))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCreateStudent))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnCreateClasses))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbListSubject)
                    .addComponent(btnCreateSubject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnCreateTeacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbListStudentPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbListStudentPropertyChange
        if ("tableCellEditor".equals(evt.getPropertyName())) {
            if (!tbListStudent.isEditing()) {
                int row = tbListStudent.getSelectedRow();
                int column = tbListStudent.getEditingColumn();

                if (row == -1 || column == 7) { // Nếu đang chỉnh sửa cột "Xóa", bỏ qua
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) tbListStudent.getModel();
                Student studentExists = null;

                try {
                    int studentId = (int) model.getValueAt(row, 0);

                    studentExists = studentDAO.getStudentById(studentId);
                    if (studentExists == null) {
                        Toast.show("❌ Sinh viên không tồn tại");
                        return;
                    }

                    String fullName = (String) model.getValueAt(row, 1);

                    Object birthDayObj = model.getValueAt(row, 2);
                    Date birthDay;

                    if (birthDayObj instanceof Date) {
                        birthDay = (Date) birthDayObj;
                    } else {
                        birthDay = Date.valueOf(birthDayObj.toString());
                    }

                    String gender = (String) model.getValueAt(row, 3);
                    String phone = (String) model.getValueAt(row, 4);
                    String address = (String) model.getValueAt(row, 5);

                    if (studentExists.getFullName().equals(fullName)
                            && studentExists.getBirthDay().equals(birthDay)
                            && studentExists.getGender().equals(gender)
                            && studentExists.getPhone().equals(phone)
                            && studentExists.getAddress().equals(address)) {

                        System.out.println("Không có thay đổi, bỏ qua cập nhật.");
                        return;
                    }

                    System.out.println("studentId: " + studentId);
                    System.out.println("fullName: " + fullName);
                    System.out.println("birthDay: " + birthDay);
                    System.out.println("gender: " + gender);
                    System.out.println("phone: " + phone);
                    System.out.println("address: " + address);

                    Student student = new Student(studentId, fullName, birthDay, gender, phone, address);

                    boolean success = studentDAO.updateStudent(student);

                    if (success) {
                        Toast.show("✅ Cập nhật sinh viên thành công!");
                    } else {
                        Toast.show("❌ Cập nhật sinh viên thất bại!");
                    }
                } catch (Exception ex) {

                    if (studentExists != null) {
                        System.out.println(studentExists);
                        model.setValueAt(studentExists.getFullName(), row, 1);
                        model.setValueAt(studentExists.getBirthDay(), row, 2);
                        model.setValueAt(studentExists.getGender(), row, 3);
                        model.setValueAt(studentExists.getPhone(), row, 4);
                        model.setValueAt(studentExists.getAddress(), row, 5);
                    }

                    System.err.println(ex);
                    ex.printStackTrace();
                    Toast.show("❌ Cập nhật sinh viên thất bại!");

                }

            }
        }
    }//GEN-LAST:event_tbListStudentPropertyChange

    private void btnCreateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateStudentActionPerformed
        AddStudentDialog addDialog = new AddStudentDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, this);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }//GEN-LAST:event_btnCreateStudentActionPerformed

    private void btnCreateClassesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClassesActionPerformed
        AddClassDialog addDialog = new AddClassDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, this);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }//GEN-LAST:event_btnCreateClassesActionPerformed

    private void tableListClassPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableListClassPropertyChange
        if ("tableCellEditor".equals(evt.getPropertyName())) {
            if (!tableListClass.isEditing()) {
                int row = tableListClass.getSelectedRow();
                int column = tableListClass.getEditingColumn();

                if (row == -1 || column == 3) {
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) tableListClass.getModel();
                Classes classesExists = null;

                try {
                    int id = (int) model.getValueAt(row, 0);

                    classesExists = classesDAO.getClassById(id);
                    if (classesExists == null) {
                        Toast.show("❌ Lớp học không tồn tại");
                        return;
                    }

                    String className = (String) model.getValueAt(row, 1);
                    String department = (String) model.getValueAt(row, 2);

                    if (classesExists.getClassName().equals(className)
                            && classesExists.getDepartment().equals(department)) {
                        System.out.println("Không có thay đổi, bỏ qua cập nhật.");
                        return;
                    }

                    Classes classes = new Classes(id, className, department);

                    boolean success = classesDAO.updateClasses(classes);

                    if (success) {
                        Toast.show("✅ Cập nhật lớp học thành công!");
                    } else {
                        Toast.show("❌ Cập nhật lớp học thất bại!");
                    }
                } catch (Exception ex) {

                    if (classesExists != null) {
                        model.setValueAt(classesExists.getClassName(), row, 1);
                        model.setValueAt(classesExists.getDepartment(), row, 2);
                    }

                    System.err.println(ex);
                    ex.printStackTrace();
                    Toast.show("❌ Cập nhật lớp học thất bại!");

                }

            }
        }
    }//GEN-LAST:event_tableListClassPropertyChange

    private void btnCreateSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSubjectActionPerformed
        AddSubjectDialog addDialog = new AddSubjectDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, this);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }//GEN-LAST:event_btnCreateSubjectActionPerformed

    private void btnCreateTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTeacherActionPerformed
        AddTeacherDialog addDialog = new AddTeacherDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, this);
        addDialog.setLocationRelativeTo(this);
        addDialog.setVisible(true);
    }//GEN-LAST:event_btnCreateTeacherActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateClasses;
    private javax.swing.JButton btnCreateStudent;
    private javax.swing.JButton btnCreateSubject;
    private javax.swing.JButton btnCreateTeacher;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbListSubject;
    private javax.swing.JTable tableListClass;
    private javax.swing.JTable tableListSubject;
    private javax.swing.JTable tableListTeacher;
    private javax.swing.JTable tbListStudent;
    // End of variables declaration//GEN-END:variables
}
