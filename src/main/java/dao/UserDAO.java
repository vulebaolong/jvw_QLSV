/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.User;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author vulebaolong
 */
public class UserDAO {

    // Thêm người dùng mới
    public boolean addUser(User user) {
        String sql = "INSERT INTO Users (email, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users ORDER BY createdAt DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getObject("studentId") != null ? rs.getInt("studentId") : null,
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Tìm người dùng theo username
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getObject("studentId") != null ? rs.getInt("studentId") : null,
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật thông tin người dùng
    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET username=?, password=?, role=?, studentId=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            if (user.getStudentId() != null) {
                stmt.setInt(4, user.getStudentId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, user.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT u.id AS userId, u.email, u.password, u.role, u.createdAt AS userCreatedAt, u.updatedAt AS userUpdatedAt, "
                + "s.id AS studentId, s.fullName, s.birthDay, s.gender, s.phone, s.address, s.classId, "
                + "s.createdAt AS studentCreatedAt, s.updatedAt AS studentUpdatedAt "
                + "FROM Users u "
                + "LEFT JOIN Students s ON u.studentId = s.id "
                + "WHERE u.id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // 🔹 Lấy thông tin User
                int userId = rs.getInt("userId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                Timestamp userCreatedAt = rs.getTimestamp("userCreatedAt");
                Timestamp userUpdatedAt = rs.getTimestamp("userUpdatedAt");

                // 🔹 Lấy thông tin Student nếu có
                Student student = null;
                Integer studentId = rs.getObject("studentId") != null ? rs.getInt("studentId") : null;
                if (studentId != null) {
                    student = new Student(
                            studentId,
                            rs.getString("fullName"),
                            rs.getDate("birthDay"),
                            rs.getString("gender"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getObject("classId") != null ? rs.getInt("classId") : null,
                            rs.getTimestamp("studentCreatedAt"),
                            rs.getTimestamp("studentUpdatedAt")
                    );
                }

                return new User(userId, email, password, role, studentId, userCreatedAt, userUpdatedAt, student);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console
        }
        return null;
    }

    // Xóa người dùng
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
