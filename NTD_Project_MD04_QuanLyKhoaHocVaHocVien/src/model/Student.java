package model;

import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student {
    private int id;
    private String studentName;
    private LocalDate dateOfBirth;
    private String email;
    private boolean sex;
    private String phone;
    private String password;
    private LocalDate createdAt;


    public Student() {

    }

    public Student(int id, String studentName, LocalDate dateOfBirth, String email, boolean sex, String phone, String password) {
        this.id = id;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;

        this.createdAt = LocalDate.now();
    }

    public Student(int studentId, String email) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("|%-5d|%-30s|%-15s|%-30s|%-6s|%-15s|%-20s|%-15s|\n",
                this.id,
                this.studentName,
                this.dateOfBirth.toString(),
                this.email,
                this.sex ? "Nam" : "Nữ",
                this.phone,
                this.password,
                this.createdAt.toString()
        );
    }


}
