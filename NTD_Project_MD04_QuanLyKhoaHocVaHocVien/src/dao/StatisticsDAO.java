package dao;

import model.statistic.CourseStudentCount;
import model.statistic.TotalStatistics;

import java.util.List;

public interface StatisticsDAO {
    TotalStatistics getTotalStudentsAndCourses();
    List<CourseStudentCount> getStudentCountPerCourse();
}
