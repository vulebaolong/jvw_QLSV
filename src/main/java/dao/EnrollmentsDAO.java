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
import model.Subject;

 
public class EnrollmentsDAO {

    public List<Enrollment> getEnrolledSubjectsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.id, e.studentId, e.subjectId, e.semester, e.year, e.grade, "
                + "s.subjectName, s.credit "
                + "FROM Enrollments e "
                + "JOIN Subjects s ON e.subjectId = s.id "
                + "WHERE e.studentId = ? "
                + "ORDER BY e.year DESC, e.semester DESC";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("id"),
                        rs.getInt("studentId"),
                        rs.getInt("subjectId"),
                        rs.getString("semester"),
                        rs.getInt("year"),
                        rs.getFloat("grade"),
                        new Subject(rs.getInt("subjectId"), rs.getString("subjectName"), rs.getInt("credit"))
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // Kiểm tra sinh viên đã đăng ký môn học chưa
    public boolean isEnrolled(int studentId, int subjectId, String semester, int year) {
        String sql = "SELECT COUNT(*) FROM Enrollments WHERE studentId = ? AND subjectId = ? AND semester = ? AND year = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);
            stmt.setString(3, semester);
            stmt.setInt(4, year);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu số lượng > 0, tức là đã đăng ký
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm đăng ký môn học với transaction
    public boolean addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollments (studentId, subjectId, semester, year, grade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); // Bắt đầu transaction

            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getSubjectId());
            stmt.setString(3, enrollment.getSemester());
            stmt.setInt(4, enrollment.getYear());
            stmt.setFloat(5, enrollment.getGrade());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                conn.commit(); // Lưu thay đổi nếu thành công
                return true;
            } else {
                conn.rollback(); // Hoàn tác nếu thất bại
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả đăng ký môn học
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollments ORDER BY createdAt DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

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
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
