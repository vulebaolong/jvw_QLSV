/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.SubjectsDAO;
import model.Subject;

import java.util.List;

 
public class TestSubjectsDAO {

    public static void main(String[] args) {
        SubjectsDAO subjectsDAO = new SubjectsDAO();

        // Thêm môn học mới
        Subject newSubject = new Subject(0, "Lập trình Java", 3);
        boolean addSuccess = subjectsDAO.addSubject(newSubject);
        System.out.println(addSuccess ? "✅ Thêm môn học thành công!" : "❌ Thêm môn học thất bại!");

        // Lấy danh sách tất cả môn học
        List<Subject> subjects = subjectsDAO.getAllSubjects();
        System.out.println("📋 Danh sách môn học:");
        for (Subject s : subjects) {
            System.out.println(s.getId() + " - " + s.getSubjectName() + " - " + s.getCredit() + " tín chỉ");
        }

        // Cập nhật môn học
        if (!subjects.isEmpty()) {
            Subject updateSubject = subjects.get(0);
            updateSubject.setSubjectName("Lập trình Python");
            updateSubject.setCredit(4);
            boolean updateSuccess = subjectsDAO.updateSubject(updateSubject);
            System.out.println(updateSuccess ? "✅ Cập nhật môn học thành công!" : "❌ Cập nhật thất bại!");
        }

        // Xóa môn học
//        if (!subjects.isEmpty()) {
//            int subjectId = subjects.get(0).getId();
//            boolean deleteSuccess = subjectsDAO.deleteSubject(subjectId);
//            System.out.println(deleteSuccess ? "✅ Xóa môn học thành công!" : "❌ Xóa môn học thất bại!");
//        }
    }
}
