package dao;

import model.Course;

import java.util.List;
import java.util.Scanner;

public interface CourseDAO {
    List<Course> showAllCourse();
    boolean addNewCourse(Course course);
    Course findCourseById(int id);
    boolean isCourseNameExist(String courseName);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
    List<Course> findCourseByName(String name);
}
