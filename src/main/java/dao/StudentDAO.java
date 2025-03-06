/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Classes;

/**
 *
 * @author vulebaolong
 */
public class StudentDAO {

    // Thêm sinh viên
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO Students (fullName, birthDay, gender, phone, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFullName());
            stmt.setDate(2, student.getBirthDay());
            stmt.setString(3, student.getGender());
            stmt.setString(4, student.getPhone());
            stmt.setString(5, student.getAddress());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách sinh viên
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.*, c.className, c.department, c.createdAt AS classCreatedAt, c.updatedAt AS classUpdatedAt "
                + "FROM Students s "
                + "LEFT JOIN Classes c ON s.classId = c.id "
                + "ORDER BY s.createdAt DESC";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // 🔹 Lấy thông tin Class nếu có
                Classes studentClass = null;
                Integer classId = rs.getObject("classId") != null ? rs.getInt("classId") : null;
                if (classId != null) {
                    studentClass = new Classes(
                            classId,
                            rs.getString("className"),
                            rs.getString("department"),
                            rs.getTimestamp("classCreatedAt"),
                            rs.getTimestamp("classUpdatedAt")
                    );
                }

                // 🔹 Lấy thông tin Student
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getDate("birthDay"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        classId,
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        studentClass // Gán thông tin Class vào Student
                );

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Cập nhật thông tin sinh viên
    public boolean updateStudent(Student student) {
        String sql = "UPDATE Students SET fullName=?, birthDay=?, gender=?, phone=?, address=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFullName());
            stmt.setDate(2, student.getBirthDay());
            stmt.setString(3, student.getGender());
            stmt.setString(4, student.getPhone());
            stmt.setString(5, student.getAddress());
            stmt.setInt(6, student.getId());

            System.err.println(stmt);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Lỗi cập nhật sinh viên! ID: " + student.getId());
            e.printStackTrace();
            return false;
        }
    }

    public Student getStudentById(int id) {
        String sql = "SELECT s.*, c.className, c.department, c.createdAt AS classCreatedAt, c.updatedAt AS classUpdatedAt "
                + "FROM Students s "
                + "LEFT JOIN Classes c ON s.classId = c.id "
                + "WHERE s.id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // 🔹 Lấy thông tin Class nếu có
                Classes studentClass = null;
                Integer classId = rs.getObject("classId") != null ? rs.getInt("classId") : null;
                if (classId != null) {
                    studentClass = new Classes(
                            classId,
                            rs.getString("className"),
                            rs.getString("department"),
                            rs.getTimestamp("classCreatedAt"),
                            rs.getTimestamp("classUpdatedAt")
                    );
                }

                // 🔹 Lấy thông tin Student
                return new Student(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getDate("birthDay"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        classId,
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        studentClass // Gán thông tin Class vào Student
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Xóa sinh viên
    public boolean deleteStudentById(int studentId) {
        String sql = "DELETE FROM Students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudentClass(int studentId, int classId) {
        String sql = "UPDATE Students SET classId = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            stmt.setInt(2, studentId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
