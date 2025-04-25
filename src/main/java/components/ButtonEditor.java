/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private int row;
    private JTable table;
    private DeleteAction deleteAction;

    public interface DeleteAction {

        void deleteRow(int row);
    }

    public ButtonEditor(String textPopup, JTable table, DeleteAction deleteAction) {
        this.table = table;
        this.deleteAction = deleteAction;
        button = new JButton("Xóa");
        button.setOpaque(true);

        // Xử lý sự kiện khi nhấn nút Xóa
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPopup == "") {
                    deleteAction.deleteRow(row);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(button, textPopup, "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteAction.deleteRow(row); // Gọi hàm deleteRow để xóa
                    }
                }

                fireEditingStopped(); // Dừng việc chỉnh sửa ô
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Xóa";
    }
}
