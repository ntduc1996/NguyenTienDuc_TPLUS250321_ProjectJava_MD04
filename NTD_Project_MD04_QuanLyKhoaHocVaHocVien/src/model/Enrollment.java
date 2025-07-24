package model;

import java.time.LocalDateTime;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDateTime registeredDate;
    private Constants.status status;

    public Enrollment() {
    }

    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registeredDate = LocalDateTime.now();
        this.status = Constants.status.WAITING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Constants.status getStatus() {
        return status;
    }

    public void setStatus(Constants.status status) {
        this.status = status;
    }
}
