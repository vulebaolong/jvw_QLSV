package session;

import java.util.prefs.Preferences;
import dao.UserDAO;
import java.util.prefs.BackingStoreException;
import model.User;
import view.MainFrame;

public class UserSession {

    private static final Preferences prefs = Preferences.userRoot().node("UserSession");

    // Lưu `userId` khi đăng nhập
    public static void saveUserId(int userId) {
        prefs.putInt("userId", userId);
    }

    // Lấy `userId` hiện tại
    public static int getUserId() {
        return prefs.getInt("userId", -1); // Nếu không có, trả về -1 (chưa đăng nhập)
    }

    // Kiểm tra xem có người dùng nào đang đăng nhập không
    public static boolean isLoggedIn() {
        return getUserId() != -1;
    }

    // Xóa `userId` (Đăng xuất)
    public static void logout() {
        prefs.remove("userId");
    }

    public static User getInfo() {
        if (UserSession.isLoggedIn()) { 
            UserDAO userDAO = new UserDAO();
            int userId = UserSession.getUserId();
            User currentUser = userDAO.getUserById(userId);
            

            if (currentUser != null) {
                System.out.println("Người dùng đã đăng nhập");
                System.out.println("Email: " + currentUser.getEmail());
                System.out.println("Role: " + currentUser.getRole());                
                System.out.println("student: " + currentUser.getStudent());                


                return currentUser;
            } else {
                UserSession.logout();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void printAllPreferences() {
        Preferences prefs = Preferences.userRoot().node("UserSession");
        try {
            String[] keys = prefs.keys(); 
            System.out.println("Danh sách dữ liệu trong Preferences:");
            for (String key : keys) {
                System.out.println(key + " = " + prefs.get(key, "Không có dữ liệu"));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clear() throws BackingStoreException {
        prefs.clear();
    }

}
