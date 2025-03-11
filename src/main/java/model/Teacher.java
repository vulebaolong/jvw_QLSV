/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.sql.Date;

/**
 *
 * @author vulebaolong
 */
public class Teacher {

    private int id;
    private String fullName;
    private Date birthDay;
    private String gender;
    private String phone;
    private String address;
    private int classId;
    private Classes classes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Teacher(int id, String fullName, Date birthDay, String gender, String phone, String address, int classId, Timestamp createdAt, Timestamp updatedAt, Classes classes) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.classId = classId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.classes = classes;
    }

    public Teacher(int id, String fullName, Date birthDay, String gender, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Teacher(int id, String fullName, Date birthDay, String gender, String phone, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Teacher(String fullName, Date birthDay, String gender, String phone, String address) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Teacher(String fullName, Date birthDay, String gender, String phone, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Teacher(int id, String fullName, Date birthDay, String gender, String phone, String address, int classId) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Classes getClasses() {
        return classes;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

}
