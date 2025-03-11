package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseInitializer {

    private static final String HOST = "localhost";
    private static final int PORT = 3307;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String DB_NAME = "db_QLSV";
    private static final String SCHEMA_FILE = "src/main/java/database/schema.sql";
    private static final String INIT_DATA_FILE = "src/main/java/database/init-data.sql";

    // 🔹 Kết nối đến MySQL mà không chỉ định database
    private static final String URI_NOT_DB = String.format("jdbc:mysql://%s:%d/?user=%s&password=%s", HOST, PORT, USER, PASSWORD);

    // 🔹 Kết nối đến MySQL với database cụ thể
    private static final String URI = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, DB_NAME, USER, PASSWORD);

    // 🔹 Kết nối cơ sở dữ liệu mà không chỉ định database
    private static Connection getRootConnection() throws SQLException {
        return DriverManager.getConnection(URI_NOT_DB);
    }

    // 🔹 Kiểm tra database có tồn tại không, nếu không thì tạo database và import file SQL
    public static void checkAndImportDatabase() {
        try (Connection conn = getRootConnection(); Statement stmt = conn.createStatement()) {

            // Kiểm tra database có tồn tại hay không
            String checkDatabase = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + DB_NAME + "'";
            ResultSet rs = stmt.executeQuery(checkDatabase);

            if (!rs.next()) {
                System.out.println("🔹 Database chưa tồn tại, đang tạo và import dữ liệu...");

                // Tạo database mới
                stmt.executeUpdate("CREATE DATABASE " + DB_NAME);
                System.out.println("✅ Database " + DB_NAME + " đã được tạo!");

                // Import file SQL vào database
                importSQLFile(SCHEMA_FILE);
                importSQLFile(INIT_DATA_FILE);
            } else {
                System.out.println("✅ Database " + DB_NAME + " đã tồn tại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Import file SQL vào database
    private static void importSQLFile(String filePath) {
        try (Connection conn = DriverManager.getConnection(URI); Statement stmt = conn.createStatement(); BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.startsWith("--")) { // Bỏ qua dòng trống & comment
                    sql.append(line).append("\n");
                    if (line.endsWith(";")) { // Khi gặp dấu `;`, thực thi câu lệnh
                        System.out.println(sql.toString());
                        stmt.executeUpdate(sql.toString());
                        sql.setLength(0); // Reset StringBuilder
                    }
                }
            }
            System.out.println("✅ Import file SQL thành công!");
        } catch (SQLException | IOException e) {
            System.err.println("❌ Lỗi khi import file SQL:");
            e.printStackTrace();
        }
    }

    // 🔹 Gọi tất cả các bước trên khi ứng dụng khởi động
    public static void initializeDatabase() {
        checkAndImportDatabase();
    }
}
