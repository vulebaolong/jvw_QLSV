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

    public boolean registerStudentByStudent(Student student, int userId) {
        String insertStudentSQL = "INSERT INTO Students (fullName, birthDay, gender, phone, address) VALUES (?, ?, ?, ?, ?)";
        String updateUserSQL = "UPDATE Users SET studentId = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmtStudent = null;
        PreparedStatement stmtUser = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // 🔹 Bắt đầu transaction

            // 🔹 1. Thêm sinh viên vào bảng Students
            stmtStudent = conn.prepareStatement(insertStudentSQL, Statement.RETURN_GENERATED_KEYS);
            stmtStudent.setString(1, student.getFullName());
            stmtStudent.setDate(2, student.getBirthDay());
            stmtStudent.setString(3, student.getGender());
            stmtStudent.setString(4, student.getPhone());
            stmtStudent.setString(5, student.getAddress());

            int affectedRows = stmtStudent.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("❌ Thêm sinh viên thất bại, không có dòng nào bị ảnh hưởng!");
            }

            // 🔹 Lấy `id` của sinh viên vừa tạo
            generatedKeys = stmtStudent.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("❌ Lỗi: Không thể lấy ID sinh viên vừa tạo!");
            }
            int studentId = generatedKeys.getInt(1); // Lấy ID mới của sinh viên
            System.out.println("✅ Student ID mới: " + studentId);
            System.out.println("✅ User ID: " + userId);

            // 🔹 2. Cập nhật studentId vào Users
            stmtUser = conn.prepareStatement(updateUserSQL);
            stmtUser.setInt(1, studentId);
            stmtUser.setInt(2, userId);

            int updatedRows = stmtUser.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("❌ Cập nhật user thất bại, không có dòng nào bị ảnh hưởng!");
            }

            // 🔹 Nếu tất cả thành công, commit transaction
            conn.commit();
            System.out.println("✅ Đăng ký sinh viên và cập nhật user thành công!");
            return true;

        } catch (SQLException e) {
            // 🔹 Rollback nếu có lỗi
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("🔄 Đã rollback! Không có thay đổi nào được thực hiện.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            // 🔹 Đóng tài nguyên
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (stmtStudent != null) {
                    stmtStudent.close();
                }
                if (stmtUser != null) {
                    stmtUser.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
                Student student = null;
                if (classId != null) {
                    studentClass = new Classes(
                            classId,
                            rs.getString("className"),
                            rs.getString("department"),
                            rs.getTimestamp("classCreatedAt"),
                            rs.getTimestamp("classUpdatedAt")
                    );
                    student = new Student(
                            rs.getInt("id"),
                            rs.getString("fullName"),
                            rs.getDate("birthDay"),
                            rs.getString("gender"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            classId,
                            rs.getTimestamp("createdAt"),
                            rs.getTimestamp("updatedAt"),
                            studentClass
                    );
                } else {
                    student = new Student(
                            rs.getInt("id"),
                            rs.getString("fullName"),
                            rs.getDate("birthDay"),
                            rs.getString("gender"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getTimestamp("createdAt"),
                            rs.getTimestamp("updatedAt")
                    );
                }

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
