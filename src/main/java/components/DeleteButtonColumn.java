/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

 
public class DeleteButtonColumn {
    public static void addDeleteButton( String text, String textPopup ,JTable table, int columnIndex, ButtonEditor.DeleteAction deleteAction) {
        TableColumnModel columnModel = table.getColumnModel();

        // Đặt Renderer cho cột
        columnModel.getColumn(columnIndex).setCellRenderer(new ButtonRenderer(text));

        // Đặt Editor cho cột với hàm xóa tùy chỉnh
        columnModel.getColumn(columnIndex).setCellEditor(new ButtonEditor(textPopup, table, deleteAction));
    }
}
