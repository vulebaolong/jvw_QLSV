/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.function.Consumer;

 
public class ActionButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private JPanel panel;
    private JButton btnDelete;
    private JButton btnChangeClass;
    private Consumer<Integer> deleteCallback;
    private Consumer<Integer> changeClassCallback;
    private int row;

    public ActionButtonColumn(JTable table, Consumer<Integer> deleteCallback, Consumer<Integer> changeClassCallback) {
        this.deleteCallback = deleteCallback;
        this.changeClassCallback = changeClassCallback;

        // Sử dụng GridLayout (1 dòng, 2 cột) để hai nút nằm ngang nhau
        panel = new JPanel(new GridLayout(1, 2, 5, 0)); // 1 hàng, 2 cột, khoảng cách 5px

        // Nút "Đổi Lớp"
        btnChangeClass = new JButton("Đổi Lớp");
        btnChangeClass.setBackground(new Color(30, 144, 255)); // Màu xanh
        btnChangeClass.setForeground(Color.WHITE);
        btnChangeClass.addActionListener(e -> changeClassCallback.accept(row));

        // Nút "Xóa"
        btnDelete = new JButton("Xóa");
        btnDelete.setBackground(new Color(220, 20, 60)); // Màu đỏ
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(btnDelete, "Bạn có chắc muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteCallback.accept(row);
                }

                fireEditingStopped(); // Dừng việc chỉnh sửa ô
            }
        });

        panel.add(btnChangeClass);
        panel.add(btnDelete);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return panel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return panel;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
