package dao;

import model.entity.Course;

import java.util.List;

public interface CourseDAO {
    List<Course> showAllCourse();
    boolean addNewCourse(Course course);
    Course findCourseById(int id);
    boolean isCourseNameExist(String courseName);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
    List<Course> findCourseByName(String name);
}
