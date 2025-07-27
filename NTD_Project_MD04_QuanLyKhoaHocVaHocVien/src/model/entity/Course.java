package model.entity;

import java.time.LocalDate;

public class Course {

    private int id;
    private String courseName;
    private int duration;
    private String instructor;
    private LocalDate createdAt;

    public Course() {
    }

    public Course(int id, String courseName, int duration, String instructor) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
        this.instructor = instructor;
        this.createdAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getDate() {
        return createdAt;
    }

    public void setDate(LocalDate date) {
        this.createdAt = date;
    }

    @Override
    public String toString() {
        return String.format("|%-5d|%-50s|%-15d|%-30s|%-15s|\n", this.id, this.courseName, this.duration, this.instructor, this.createdAt.toString());
    }

}
