package components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Toast {

    private static final CopyOnWriteArrayList<JWindow> toastList = new CopyOnWriteArrayList<>();
    private static int duration = 2000;
    private static int fadeSpeed = 50;
    private static Position position;
    private static JFrame parentFrame;

    public enum Position {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CENTER
    }

    public static void init(JFrame parent, int durationMs, Position pos) {
        parentFrame = parent;
        duration = durationMs;
        position = pos;
    }

    public static void show(String message) {
        if (parentFrame == null) {
            throw new IllegalStateException("Toast chưa được khởi tạo! Hãy gọi Toast.init() trước.");
        }

        JWindow toastWindow = new JWindow();
        toastWindow.setAlwaysOnTop(true);
        toastWindow.setOpacity(0f);
        toastWindow.setFocusableWindowState(false);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(50, 50, 50));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.setPreferredSize(new Dimension(300, 50));

        toastWindow.setLayout(new BorderLayout());
        toastWindow.add(label, BorderLayout.CENTER);
        toastWindow.pack();

        updateToastPosition(toastWindow);
        toastWindow.setVisible(true);
        slideIn(toastWindow);
        toastList.add(toastWindow);
    }

    private static void updateToastPosition(JWindow toastWindow) {
        Rectangle parentBounds = parentFrame.getBounds();
        int startX = 0, startY = 0;

        switch (position) {
            case TOP_LEFT:
                startX = parentBounds.x + 10;
                startY = parentBounds.y + 10;
                break;
            case TOP_RIGHT:
                startX = parentBounds.x + parentBounds.width - toastWindow.getWidth() - 10;
                startY = parentBounds.y + 10;
                break;
            case BOTTOM_LEFT:
                startX = parentBounds.x + 10;
                startY = parentBounds.y + parentBounds.height - toastWindow.getHeight() - 10;
                break;
            case BOTTOM_RIGHT:
                startX = parentBounds.x + parentBounds.width - toastWindow.getWidth() - 10;
                startY = parentBounds.y + parentBounds.height - 10;
                break;
            case CENTER:
                startX = parentBounds.x + (parentBounds.width - toastWindow.getWidth()) / 2;
                startY = parentBounds.y + (parentBounds.height - toastWindow.getHeight()) / 2;
                break;
        }

        // Điều chỉnh vị trí để không trùng nhau
        for (JWindow existingToast : toastList) {
            if (existingToast.getY() == startY) {
                startY += toastWindow.getHeight() + 5;
            }
        }

        toastWindow.setLocation(startX, startY);
    }

    private static void slideIn(JWindow toastWindow) {
        new Thread(() -> {
            int targetY = toastWindow.getY() - 50;
            int startY = toastWindow.getY();

            for (float opacity = 0f; opacity <= 1f; opacity += 0.1f) {
                toastWindow.setOpacity(opacity);
                toastWindow.setLocation(toastWindow.getX(), startY - (int) ((startY - targetY) * opacity));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }

            restartTimer(toastWindow);
        }).start();
    }

    private static void restartTimer(JWindow toastWindow) {
        new Thread(() -> {
            try {
                Thread.sleep(duration);
                fadeOut(toastWindow);
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

    private static void fadeOut(JWindow toastWindow) {
        new Thread(() -> {
            try {
                for (float opacity = 1f; opacity > 0f; opacity -= 0.05f) {
                    toastWindow.setOpacity(opacity);
                    Thread.sleep(fadeSpeed);
                }
                toastWindow.setVisible(false);
                toastWindow.dispose();
                toastList.remove(toastWindow);
            } catch (InterruptedException ignored) {
            }
        }).start();
    }
}
