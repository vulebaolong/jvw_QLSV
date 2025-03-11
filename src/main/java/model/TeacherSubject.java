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
public class TeacherSubject {

    private int id;
    private int teacherId;
    private int subjectId;
    private Subject subject;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 🔹 Constructor (Create)
    public TeacherSubject(int teacherId, int subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    // 🔹 Constructor (Full)
    public TeacherSubject(int id, int teacherId, int subjectId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public TeacherSubject(int id, int teacherId, int subjectId, Subject subject ,Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.subject = subject;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
