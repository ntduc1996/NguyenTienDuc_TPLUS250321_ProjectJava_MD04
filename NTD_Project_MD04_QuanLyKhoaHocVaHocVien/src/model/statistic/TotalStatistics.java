package model.statistic;

public class TotalStatistics {
    private int totalStudents;
    private int totalCourses;

    public TotalStatistics() {
    }

    public TotalStatistics(int totalStudents, int totalCourses) {
        this.totalStudents = totalStudents;
        this.totalCourses = totalCourses;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    @Override
    public String toString() {
        return String.format("|%-30d|%-30d|", this.totalStudents,this.totalCourses);
    }
}
