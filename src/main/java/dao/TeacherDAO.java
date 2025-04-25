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
import model.Teacher;
import model.User;

 
public class TeacherDAO {
    
    

    public boolean isTeaching(int teacherId, int subjectId) {
        String sql = "SELECT COUNT(*) FROM TeacherSubject WHERE teacherId = ? AND subjectId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            stmt.setInt(2, subjectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTeacherWithTransaction(User user, Teacher teacher) {
        Connection conn = null;
        PreparedStatement stmtUser = null;
        PreparedStatement stmtTeacher = null;
        PreparedStatement stmtUpdateUser = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // 🔹 Bắt đầu transaction

            // 🔹 1. Thêm User vào Users
            String sqlUser = "INSERT INTO Users (email, password, role) VALUES (?, ?, 'Teacher')";
            stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, user.getEmail());
            stmtUser.setString(2, user.getPassword());

            int rowsInsertedUser = stmtUser.executeUpdate();
            if (rowsInsertedUser == 0) {
                conn.rollback();
                return false;
            }

            // 🔹 2. Lấy ID của User vừa tạo
            ResultSet generatedKeys = stmtUser.getGeneratedKeys();
            int userId;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // 🔹 3. Thêm Teacher vào bảng Teachers
            String sqlTeacher = "INSERT INTO Teachers (fullName, birthDay, gender, phone, address) VALUES (?, ?, ?, ?, ?)";
            stmtTeacher = conn.prepareStatement(sqlTeacher, Statement.RETURN_GENERATED_KEYS);
            stmtTeacher.setString(1, teacher.getFullName());
            stmtTeacher.setDate(2, teacher.getBirthDay());
            stmtTeacher.setString(3, teacher.getGender());
            stmtTeacher.setString(4, teacher.getPhone());
            stmtTeacher.setString(5, teacher.getAddress());

            int rowsInsertedTeacher = stmtTeacher.executeUpdate();
            if (rowsInsertedTeacher == 0) {
                conn.rollback();
                return false;
            }

            // 🔹 4. Lấy ID của Teacher vừa tạo
            ResultSet generatedKeysTeacher = stmtTeacher.getGeneratedKeys();
            int teacherId;
            if (generatedKeysTeacher.next()) {
                teacherId = generatedKeysTeacher.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // 🔹 5. Cập nhật bảng Users để liên kết với Teacher
            String sqlUpdateUser = "UPDATE Users SET teacherId = ? WHERE id = ?";
            stmtUpdateUser = conn.prepareStatement(sqlUpdateUser);
            stmtUpdateUser.setInt(1, teacherId);
            stmtUpdateUser.setInt(2, userId);

            int rowsUpdated = stmtUpdateUser.executeUpdate();
            if (rowsUpdated == 0) {
                conn.rollback();
                return false;
            }

            // 🔹 6. Commit transaction nếu thành công
            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Nếu có lỗi, rollback transaction
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (stmtUser != null) {
                    stmtUser.close();
                }
                if (stmtTeacher != null) {
                    stmtTeacher.close();
                }
                if (stmtUpdateUser != null) {
                    stmtUpdateUser.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean registerTeacherByTeacher(Teacher teacher, int userId) {
        String insertStudentSQL = "INSERT INTO Teachers (fullName, birthDay, gender, phone, address) VALUES (?, ?, ?, ?, ?)";
        String updateUserSQL = "UPDATE Users SET studentId = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmtTeacher = null;
        PreparedStatement stmtUser = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // 🔹 Bắt đầu transaction

            stmtTeacher = conn.prepareStatement(insertStudentSQL, Statement.RETURN_GENERATED_KEYS);
            stmtTeacher.setString(1, teacher.getFullName());
            stmtTeacher.setDate(2, teacher.getBirthDay());
            stmtTeacher.setString(3, teacher.getGender());
            stmtTeacher.setString(4, teacher.getPhone());
            stmtTeacher.setString(5, teacher.getAddress());

            int affectedRows = stmtTeacher.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("❌ Thêm giáo viên thất bại, không có dòng nào bị ảnh hưởng!");
            }

            generatedKeys = stmtTeacher.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new SQLException("❌ Lỗi: Không thể lấy ID giáo viên vừa tạo!");
            }
            int teacherId = generatedKeys.getInt(1); // Lấy ID mới của sinh viên
            System.out.println("✅ Teacher ID mới: " + teacherId);
            System.out.println("✅ User ID: " + userId);

            // 🔹 2. Cập nhật teacherId vào Users
            stmtUser = conn.prepareStatement(updateUserSQL);
            stmtUser.setInt(1, teacherId);
            stmtUser.setInt(2, userId);

            int updatedRows = stmtUser.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("❌ Cập nhật giáo viên thất bại, không có dòng nào bị ảnh hưởng!");
            }

            // 🔹 Nếu tất cả thành công, commit transaction
            conn.commit();
            System.out.println("✅ Đăng ký giáo viên và cập nhật user thành công!");
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
                if (stmtTeacher != null) {
                    stmtTeacher.close();
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

    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teachers ORDER BY createdAt DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Teacher tec = new Teacher(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getDate("birthDay"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
                teachers.add(tec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public boolean updateTeacher(Teacher teacher) {
        String sql = "UPDATE Teachers SET fullName=?, birthDay=?, gender=?, phone=?, address=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getFullName());
            stmt.setDate(2, teacher.getBirthDay());
            stmt.setString(3, teacher.getGender());
            stmt.setString(4, teacher.getPhone());
            stmt.setString(5, teacher.getAddress());
            stmt.setInt(6, teacher.getId());

            System.err.println(stmt);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Lỗi cập nhật giảng viên! ID: " + teacher.getId());
            e.printStackTrace();
            return false;
        }
    }

    public Teacher getTeacherById(int id) {
        String sql = "SELECT t.*, c.className, c.department, c.createdAt AS classCreatedAt, c.updatedAt AS classUpdatedAt "
                + "FROM Teachers t "
                + "LEFT JOIN Classes c ON t.classId = c.id "
                + "WHERE t.id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // 🔹 Lấy thông tin Class nếu có
                Classes teacherClass = null;
                Integer classId = rs.getObject("classId") != null ? rs.getInt("classId") : null;
                if (classId != null) {
                    teacherClass = new Classes(
                            classId,
                            rs.getString("className"),
                            rs.getString("department"),
                            rs.getTimestamp("classCreatedAt"),
                            rs.getTimestamp("classUpdatedAt")
                    );
                }

                // 🔹 Lấy thông tin giảng viên
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getDate("birthDay"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        classId,
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt"),
                        teacherClass // Gán thông tin Class vào Teacher
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteTeacherById(int teacherId) {
        String sql = "DELETE FROM Teachers WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTeacherByIdWidthTransaction(int teacherId) {
        String deleteTeacherSQL = "DELETE FROM Teachers WHERE id=?";
        String deleteUserSQL = "DELETE FROM Users WHERE teacherId=?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            // 🔹 Xóa tài khoản User trước
            try (PreparedStatement stmtUser = conn.prepareStatement(deleteUserSQL)) {
                stmtUser.setInt(1, teacherId);
                stmtUser.executeUpdate();
            }

            // 🔹 Xóa Teacher sau
            try (PreparedStatement stmtTeacher = conn.prepareStatement(deleteTeacherSQL)) {
                stmtTeacher.setInt(1, teacherId);
                int affectedRows = stmtTeacher.executeUpdate();

                if (affectedRows > 0) {
                    conn.commit(); // Nếu cả hai đều thành công -> commit transaction
                    return true;
                } else {
                    conn.rollback(); // Nếu không có teacher nào bị xóa, rollback lại
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.rollback(); // Rollback nếu có lỗi
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

    public boolean updateTeacherClass(int teacherId, int classId) {
        String sql = "UPDATE Teachers SET classId = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            stmt.setInt(2, teacherId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
