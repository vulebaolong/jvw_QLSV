/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.StudentDAO;
import model.Student;

import java.sql.Date;
import java.util.List;

 
public class TestStudentDAO {

    private static final StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {
//        addStudent();
        getAllStudents();
//        updateStudentById(1, "Nguyễn Văn B");
//        deleteStudentById(2);
    }

    // Thêm sinh viên mới
    public static void addStudent() {
        Student newStudent = new Student("Nguyễn Văn A", Date.valueOf("2002-05-15"), "Male", "0987654321", "Hà Nội");
        boolean addSuccess = studentDAO.addStudent(newStudent);
        System.out.println(addSuccess ? "✅ Thêm sinh viên thành công!" : "❌ Thêm sinh viên thất bại!");
    }

    // Lấy danh sách tất cả sinh viên
    public static void getAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        System.out.println("📋 Danh sách sinh viên:");
        for (Student s : students) {
            System.out.println(s.getId() + " - " + s.getFullName() + " - " + s.getUpdatedAt() + " - " + s.getCreatedAt());
        }
    }

    // Cập nhật tên sinh viên theo ID
    public static void updateStudentById(int studentId, String newName) {
        Student student = studentDAO.getStudentById(studentId);
        if (student != null) {
            student.setFullName(newName);
            boolean updateSuccess = studentDAO.updateStudent(student);
            System.out.println(updateSuccess ? "✅ Cập nhật thành công!" : "❌ Cập nhật thất bại!");
        } else {
            System.out.println("❌ Không tìm thấy sinh viên có ID: " + studentId);
        }
    }

    // Xóa sinh viên theo ID
    public static void deleteStudentById(int studentId) {
        boolean deleteSuccess = studentDAO.deleteStudentById(studentId);
        System.out.println(deleteSuccess ? "✅ Xóa sinh viên có ID " + studentId + " thành công!" : "❌ Xóa thất bại hoặc không tìm thấy sinh viên!");
    }
}
