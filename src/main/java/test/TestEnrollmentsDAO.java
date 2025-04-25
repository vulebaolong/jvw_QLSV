/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.EnrollmentsDAO;
import model.Enrollment;
import java.util.List;

 
public class TestEnrollmentsDAO {

    public static void main(String[] args) {
        EnrollmentsDAO enrollmentsDAO = new EnrollmentsDAO();

        // Thêm đăng ký môn học mới
        Enrollment newEnrollment = new Enrollment(0, 1, 2, "Fall", 2024, 8.5f, null, null);
        boolean addSuccess = enrollmentsDAO.addEnrollment(newEnrollment);
        System.out.println(addSuccess ? "✅ Thêm đăng ký môn học thành công!" : "❌ Thêm đăng ký môn học thất bại!");

        // Lấy danh sách tất cả đăng ký môn học
        List<Enrollment> enrollments = enrollmentsDAO.getAllEnrollments();
        System.out.println("📋 Danh sách đăng ký môn học:");
        for (Enrollment e : enrollments) {
            System.out.println(e.getId() + " - Sinh viên: " + e.getStudentId() + " - Môn học: " + e.getSubjectId()
                    + " - Học kỳ: " + e.getSemester() + " - Năm: " + e.getYear() + " - Điểm: " + e.getGrade() + " - " + e.getUpdatedAt() + " - " + e.getCreatedAt());
        }

        // Cập nhật điểm môn học
        if (!enrollments.isEmpty()) {
            Enrollment updateEnrollment = enrollments.get(0);
            boolean updateSuccess = enrollmentsDAO.updateEnrollmentGrade(updateEnrollment.getId(), 9.5f);
            System.out.println(updateSuccess ? "✅ Cập nhật điểm thành công!" : "❌ Cập nhật điểm thất bại!");
        }

        // Xóa đăng ký môn học
        if (!enrollments.isEmpty()) {
            int enrollmentId = enrollments.get(0).getId();
            boolean deleteSuccess = enrollmentsDAO.deleteEnrollment(enrollmentId);
            System.out.println(deleteSuccess ? "✅ Xóa đăng ký môn học thành công!" : "❌ Xóa đăng ký môn học thất bại!");
        }
    }
}
