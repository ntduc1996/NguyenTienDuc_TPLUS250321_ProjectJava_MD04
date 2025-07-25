package model.statistic;

public class CourseStudentCount {
    private int courseId;
    private String courseName;
    private int studentCount;

    public CourseStudentCount() {
    }

    public CourseStudentCount(int courseId, String courseName, int studentCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentCount = studentCount;
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

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return String.format("|%-20d|%-55s|%-30d|", this.courseId, this.courseName, this.studentCount);
    }
}
