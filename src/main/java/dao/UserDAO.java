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
    // Đăng ký sinh viên (thêm vào bảng Students và Users)

    public boolean registerStudent(Student student, String username, String password) {
        Connection conn = null;
        PreparedStatement stmtStudent = null;
        PreparedStatement stmtUser = null;
        ResultSet generatedKeys = null;

        String sqlStudent = "INSERT INTO Students (fullName, birthDay, gender, email, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlUser = "INSERT INTO Users (username, password, role, studentId) VALUES (?, ?, 'Student', ?)";

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            // Thêm sinh viên vào bảng Students
            stmtStudent = conn.prepareStatement(sqlStudent, Statement.RETURN_GENERATED_KEYS);
            stmtStudent.setString(1, student.getFullName());
            stmtStudent.setDate(2, student.getBirthDay());
            stmtStudent.setString(3, student.getGender());
            stmtStudent.setString(4, student.getPhone());
            stmtStudent.setString(5, student.getAddress());
            int affectedRows = stmtStudent.executeUpdate();

            if (affectedRows == 0) {
                conn.rollback();
                return false;
            }

            // Lấy ID của sinh viên mới
            generatedKeys = stmtStudent.getGeneratedKeys();
            int studentId;
            if (generatedKeys.next()) {
                studentId = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // Thêm tài khoản vào bảng Users
            stmtUser = conn.prepareStatement(sqlUser);
            stmtUser.setString(1, username);
            stmtUser.setString(2, hashPassword(password)); // Mã hóa mật khẩu trước khi lưu
            stmtUser.setInt(3, studentId);

            affectedRows = stmtUser.executeUpdate();
            if (affectedRows == 0) {
                conn.rollback();
                return false;
            }

            conn.commit(); // Xác nhận giao dịch
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Quay lại trạng thái ban đầu nếu lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
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
                    conn.setAutoCommit(true); // Bật lại AutoCommit
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm mã hóa mật khẩu bằng SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi mã hóa mật khẩu!", e);
        }
    }

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
        String sql = "SELECT * FROM Users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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
