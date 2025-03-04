/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vulebaolong
 */
public class EnrollmentsDAO {
    
    // Thêm đăng ký môn học
    public boolean addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollments (studentId, subjectId, semester, year, grade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getSubjectId());
            stmt.setString(3, enrollment.getSemester());
            stmt.setInt(4, enrollment.getYear());
            stmt.setFloat(5, enrollment.getGrade());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả đăng ký môn học
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollments ORDER BY createdAt DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("id"),
                        rs.getInt("studentId"),
                        rs.getInt("subjectId"),
                        rs.getString("semester"),
                        rs.getInt("year"),
                        rs.getFloat("grade"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // Cập nhật điểm môn học
    public boolean updateEnrollmentGrade(int enrollmentId, float newGrade) {
        String sql = "UPDATE Enrollments SET grade=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, newGrade);
            stmt.setInt(2, enrollmentId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa đăng ký môn học
    public boolean deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM Enrollments WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
