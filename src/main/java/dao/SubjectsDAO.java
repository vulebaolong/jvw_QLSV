/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vulebaolong
 */
public class SubjectsDAO {

    // Thêm môn học
    public boolean addSubject(Subject subject) {
        String sql = "INSERT INTO Subjects (subjectName, credit) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getSubjectName());
            stmt.setInt(2, subject.getCredit());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả môn học
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subjects ORDER BY createdAt DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getInt("id"),
                        rs.getString("subjectName"),
                        rs.getInt("credit"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // Lấy danh sách tất cả môn học chưa đăng ký
    public List<Subject> getUnregisteredSubjects(int studentId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT s.id, s.subjectName, s.credit, s.createdAt, s.updatedAt "
                + "FROM Subjects s "
                + "LEFT JOIN Enrollments e ON s.id = e.subjectId AND e.studentId = ? "
                + "WHERE e.subjectId IS NULL "
                + "ORDER BY s.createdAt DESC";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getInt("id"),
                        rs.getString("subjectName"),
                        rs.getInt("credit"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public List<Subject> getUnregisteredSubjectsForTeacher(int teacherId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT s.id, s.subjectName, s.credit, s.createdAt, s.updatedAt "
                + "FROM Subjects s "
                + "LEFT JOIN TeacherSubject ts ON s.id = ts.subjectId AND ts.teacherId = ? "
                + "WHERE ts.subjectId IS NULL "
                + "ORDER BY s.createdAt DESC";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getInt("id"),
                        rs.getString("subjectName"),
                        rs.getInt("credit"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // Cập nhật thông tin môn học
    public boolean updateSubject(Subject subject) {
        String sql = "UPDATE Subjects SET subjectName=?, credit=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getSubjectName());
            stmt.setInt(2, subject.getCredit());
            stmt.setInt(3, subject.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Subject getSubjectById(int id) {
        String sql = "SELECT * FROM Subjects WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Subject(
                        rs.getInt("id"),
                        rs.getString("subjectName"),
                        rs.getInt("credit"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Xóa môn học
    public boolean deleteSubject(int subjectId) {
        String sql = "DELETE FROM Subjects WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
