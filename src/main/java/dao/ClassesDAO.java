/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vulebaolong
 */
public class ClassesDAO {
    // Thêm lớp học vào database (không cần ID)
    public boolean addClass(Classes cls) {
        String sql = "INSERT INTO Classes (className, department) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cls.getClassName());
            stmt.setString(2, cls.getDepartment());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả lớp học
    public List<Classes> getAllClasses() {
        List<Classes> classes = new ArrayList<>();
        String sql = "SELECT * FROM Classes";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Classes cls = new Classes(
                        rs.getInt("id"),
                        rs.getString("className"),
                        rs.getString("department")
                );
                classes.add(cls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    // Cập nhật thông tin lớp theo ID
    public boolean updateClassById(int classId, String newName, String newDepartment) {
        String sql = "UPDATE Classes SET className=?, department=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newDepartment);
            stmt.setInt(3, classId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa lớp học theo ID
    public boolean deleteClassById(int classId) {
        String sql = "DELETE FROM Classes WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
