/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author vulebaolong
 */
public class Enrollment {

    private int id;
    private int studentId;
    private int subjectId;
    private String semester;
    private int year;
    private float grade;
    private Subject subject;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Enrollment(int id, int studentId, int subjectId, String semester, int year, float grade, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Enrollment(int studentId, int subjectId, String semester, int year, int grade) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
    }

    public Enrollment(int id, int studentId, int subjectId, String semester, int year, float grade, Subject subject) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
        this.subject = subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public float getGrade() {
        return grade;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
