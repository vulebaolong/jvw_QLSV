/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Subject;
import model.TeacherSubject;

 
public class TeacherSubjectDAO {

    public List<TeacherSubject> getClassesByTeacherId(int teacherId) {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        String sql = "SELECT ts.id, ts.teacherId, ts.subjectId, s.subjectName, s.credit, ts.createdAt, ts.updatedAt "
                + "FROM TeacherSubject ts "
                + "JOIN Subjects s ON ts.subjectId = s.id "
                + "WHERE ts.teacherId = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getInt("subjectId"),
                        rs.getString("subjectName"),
                        rs.getInt("credit"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );

                TeacherSubject teacherSubject = new TeacherSubject(
                        rs.getInt("id"),
                        rs.getInt("teacherId"),
                        rs.getInt("subjectId"),
                        subject,
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );

                teacherSubjects.add(teacherSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherSubjects;
    }

    // 🔹 Thêm giảng viên dạy môn học
    public boolean addTeacherSubject(TeacherSubject teacherSubject) {
        String sql = "INSERT INTO TeacherSubject (teacherId, subjectId) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherSubject.getTeacherId());
            stmt.setInt(2, teacherSubject.getSubjectId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Lấy danh sách tất cả các giáo viên dạy môn học
    public List<TeacherSubject> getAllTeacherSubjects() {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        String sql = "SELECT * FROM TeacherSubject ORDER BY createdAt DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TeacherSubject teacherSubject = new TeacherSubject(
                        rs.getInt("id"),
                        rs.getInt("teacherId"),
                        rs.getInt("subjectId"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                teacherSubjects.add(teacherSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherSubjects;
    }

    // 🔹 Cập nhật giảng viên dạy môn học
    public boolean updateTeacherSubject(TeacherSubject teacherSubject) {
        String sql = "UPDATE TeacherSubject SET teacherId=?, subjectId=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherSubject.getTeacherId());
            stmt.setInt(2, teacherSubject.getSubjectId());
            stmt.setInt(3, teacherSubject.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Xóa giảng viên dạy môn học
    public boolean deleteTeacherSubject(int id) {
        String sql = "DELETE FROM TeacherSubject WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Lấy danh sách môn học mà giảng viên đang dạy
    public List<TeacherSubject> getSubjectsByTeacherId(int teacherId) {
        List<TeacherSubject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM TeacherSubject WHERE teacherId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                subjects.add(new TeacherSubject(
                        rs.getInt("id"),
                        rs.getInt("teacherId"),
                        rs.getInt("subjectId"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // 🔹 Lấy danh sách giảng viên dạy một môn học
    public List<TeacherSubject> getTeachersBySubjectId(int subjectId) {
        List<TeacherSubject> teachers = new ArrayList<>();
        String sql = "SELECT * FROM TeacherSubject WHERE subjectId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                teachers.add(new TeacherSubject(
                        rs.getInt("id"),
                        rs.getInt("teacherId"),
                        rs.getInt("subjectId"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}
