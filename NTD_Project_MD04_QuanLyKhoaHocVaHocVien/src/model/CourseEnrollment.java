package model;

import java.time.LocalDate;

public class CourseEnrollment {
    private int courseId;
    private String courseName;
    private String instructor;
    private LocalDate eRegisterAt;
    private Constants.status eStatus;

    public CourseEnrollment() {
    }

    public CourseEnrollment(int courseId, String courseName, String instructor, LocalDate eRegisterAt, Constants.status eStatus) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.eRegisterAt = eRegisterAt;
        this.eStatus = eStatus;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate geteRegisterAt() {
        return eRegisterAt;
    }

    public void seteRegisterAt(LocalDate eRegisterAt) {
        this.eRegisterAt = eRegisterAt;
    }

    public Constants.status geteStatus() {
        return eStatus;
    }

    public void seteStatus(Constants.status eStatus) {
        this.eStatus = eStatus;
    }

    @Override
    public String toString() {
        return String.format("|%-5d|%-55s|%-20s|%-30s|%-15s|\n",this.courseId, this.courseName, this.instructor,this.eRegisterAt, this.eStatus);
    }
}
