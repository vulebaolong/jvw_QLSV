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
public class Classes {

    private int id;
    private String className;
    private String department;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
     public Classes(int id, String className, String department, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.className = className;
        this.department = department;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Classes(int id, String className, String department) {
        this.id = id;
        this.className = className;
        this.department = department;
    }
    
    public Classes(String className, String department) {
        this.className = className;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getDepartment() {
        return department;
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

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
