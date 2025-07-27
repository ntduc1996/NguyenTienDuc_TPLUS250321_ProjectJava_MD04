package model;

public class CombineCES {
    private int enrollmentId;
    private int courseId;
    private int studentId;
    private String courseName;
    private String studentName;
    private Constants.status status;

    public CombineCES() {
    }

    public CombineCES(int enrollmentId, int courseId, int studentId, String courseName, String studentName, Constants.status status) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.courseName = courseName;
        this.studentName = studentName;
        this.status = status;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Constants.status getStatus() {
        return status;
    }

    public void setStatus(Constants.status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("|%-15d|%-15d|%-15d|%-50s|%-30s|%-10s|\n"
              , this.enrollmentId, this.courseId, this.studentId, this.courseName, this.studentName, this.status);
    }
}
