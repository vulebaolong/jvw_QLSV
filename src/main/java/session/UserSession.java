package session;

import java.util.prefs.Preferences;
import dao.UserDAO;
import java.util.prefs.BackingStoreException;
import model.User;

public class UserSession {

    private static final Preferences prefs = Preferences.userRoot().node("UserSession");

    public static void saveUserId(int userId) {
        prefs.putInt("userId", userId);
    }

    public static int getUserId() {
        return prefs.getInt("userId", -1);
    }

    public static boolean isLoggedIn() {
        return getUserId() != -1;
    }

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

    public static void saveRememberMeState(boolean isChecked) {
        Preferences prefs = Preferences.userRoot().node(UserSession.class.getName());
        prefs.putBoolean("rememberMe", isChecked);
    }

    public static boolean getRememberMeState() {
        Preferences prefs = Preferences.userRoot().node(UserSession.class.getName());
        return prefs.getBoolean("rememberMe", false);
    }

}
