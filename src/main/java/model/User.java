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
public class User {

    private int id;
    private String email;
    private String password;
    private String role;
    private Integer studentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;    
    private Student student;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password, String role, Integer studentId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.studentId = studentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(int userId, String email, String password, String role, Integer studentId, Timestamp createdAt, Timestamp updatedAt, Student student) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.studentId = studentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.student = student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Integer getStudentId() {
        return studentId;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
