/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vulebaolong
 */
public class Classes {

    private int id;
    private String className;
    private String department;

    // Constructor dùng khi lấy từ database (có ID)
    public Classes(int id, String className, String department) {
        this.id = id;
        this.className = className;
        this.department = department;
    }

    // Constructor dùng khi thêm mới (không cần ID)
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

    public void setId(int id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
