/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author vulebaolong
 */
public class DeleteButtonColumn {
    public static void addDeleteButton(JTable table, int columnIndex, ButtonEditor.DeleteAction deleteAction) {
        TableColumnModel columnModel = table.getColumnModel();

        // Đặt Renderer cho cột
        columnModel.getColumn(columnIndex).setCellRenderer(new ButtonRenderer("Xóa"));

        // Đặt Editor cho cột với hàm xóa tùy chỉnh
        columnModel.getColumn(columnIndex).setCellEditor(new ButtonEditor(table, deleteAction));
    }
}
