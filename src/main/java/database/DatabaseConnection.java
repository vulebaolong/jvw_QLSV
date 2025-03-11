/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vulebaolong
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/db_QLSV";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("⚠️ Lỗi: Không tìm thấy MySQL Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("⚠️ Lỗi: Kết nối MySQL thất bại!");
            e.printStackTrace();
        }
        return conn;
    }
    
}
    