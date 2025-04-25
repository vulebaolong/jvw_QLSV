/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.UserDAO;
import model.User;
import java.util.List;

 
public class TestUserDAO {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Thêm người dùng mới
        User newUser = new User(0, "admin", "hashed_password", "Admin", null, null, null);
        boolean addSuccess = userDAO.addUser(newUser);
        System.out.println(addSuccess ? "✅ Thêm người dùng thành công!" : "❌ Thêm người dùng thất bại!");

        // Lấy danh sách tất cả người dùng
        List<User> users = userDAO.getAllUsers();
        System.out.println("📋 Danh sách người dùng:");
        for (User u : users) {
            System.out.println(u.getId() + " - " + u.getEmail()+ " - " + u.getRole());
        }

        // Tìm người dùng theo username
        User foundUser = userDAO.getUserByEmail("admin");
        if (foundUser != null) {
            System.out.println("🔍 Tìm thấy người dùng: " + foundUser.getEmail()+ " - Vai trò: " + foundUser.getRole());
        } else {
            System.out.println("❌ Không tìm thấy người dùng!");
        }

        // Cập nhật thông tin người dùng
        if (!users.isEmpty()) {
            User updateUser = users.get(0);
            updateUser.setEmail("admin_updated");
            boolean updateSuccess = userDAO.updateUser(updateUser);
            System.out.println(updateSuccess ? "✅ Cập nhật người dùng thành công!" : "❌ Cập nhật thất bại!");
        }

        // Xóa người dùng
//        if (!users.isEmpty()) {
//            int userId = users.get(0).getId();
//            boolean deleteSuccess = userDAO.deleteUser(userId);
//            System.out.println(deleteSuccess ? "✅ Xóa người dùng thành công!" : "❌ Xóa người dùng thất bại!");
//        }
    }
}
