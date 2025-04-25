/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.ClassesDAO;
import model.Classes;

import java.util.List;
import java.util.Random;

 
public class TestClassDAO {

     private static  ClassesDAO classesDAO = new ClassesDAO();

    public static void main(String[] args) {

        // ✅ Thêm 20 lớp học vào database
        createBulkClasses();

        // ✅ Đọc danh sách lớp học
        read();

        // ✅ Cập nhật lớp học theo ID
//        updateClassById(classesDAO, 1, "IT99", "Khoa học máy tính nâng cao");
        // ✅ Xóa lớp học theo ID
//        deleteClassById(classesDAO, 2);
    }

    // Tạo 20 lớp học tự động
    private static void createBulkClasses() {
        String[] departments = {
            "Công nghệ thông tin", "Khoa học máy tính", "Hệ thống thông tin",
            "An toàn thông tin", "Kỹ thuật phần mềm", "Khoa học dữ liệu",
            "Mạng máy tính", "Trí tuệ nhân tạo", "Khoa học máy tính ứng dụng",
            "Công nghệ phần mềm", "Hệ thống nhúng", "Điện tử viễn thông"
        };

        String[] classNames = {
            "IT01", "IT02", "IT03",
            "CS01", "CS02", "CS03",
            "SE01", "SE02", "SE03",
            "DS01", "DS02", "DS03"
        };

        for (int i = 0; i < classNames.length; i++) {
            String className = classNames[i];
            String department = departments[i % departments.length]; // Lặp lại danh sách departments

            Classes newClass = new Classes(className, department);
            boolean addSuccess = classesDAO.addClass(newClass);
            System.out.println(addSuccess
                    ? "✅ Thêm lớp " + className + " - " + department + " thành công!"
                    : "❌ Thêm lớp " + className + " thất bại!");
        }
    }

    // Đọc danh sách lớp học
    private static void read() {
        List<Classes> classes = classesDAO.getAllClasses();
        System.out.println("📋 Danh sách lớp học:");
        for (Classes c : classes) {
            System.out.println(c.getId() + " - " + c.getClassName() + " - " + c.getDepartment());
        }
    }

    // Cập nhật thông tin lớp theo ID
    private static void updateClassById(int classId, String newName, String newDepartment) {
        boolean updateSuccess = classesDAO.updateClassById(classId, newName, newDepartment);
        System.out.println(updateSuccess
                ? "✅ Cập nhật lớp " + classId + " thành công!"
                : "❌ Cập nhật lớp " + classId + " thất bại!");
    }

    // Xóa lớp học theo ID
    private static void deleteClassById(int classId) {
        boolean deleteSuccess = classesDAO.deleteClassById(classId);
        System.out.println(deleteSuccess
                ? "✅ Xóa lớp " + classId + " thành công!"
                : "❌ Xóa lớp " + classId + " thất bại!");
    }

}
