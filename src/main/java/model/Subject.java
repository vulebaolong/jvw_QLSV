/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

 
public class Subject {

    private int id;
    private String subjectName;
    private int credit;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Subject(int id, String subjectName, int credit) {
        this.id = id;
        this.subjectName = subjectName;
        this.credit = credit;
    }
    
     public Subject(String subjectName, int credit) {
        this.subjectName = subjectName;
        this.credit = credit;
    }

    public Subject(int id, String subjectName, int credit, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.subjectName = subjectName;
        this.credit = credit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
