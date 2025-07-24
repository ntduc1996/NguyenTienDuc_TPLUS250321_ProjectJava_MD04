package business;

import model.Course;

import java.util.List;
import java.util.Scanner;

public interface ICourseServices {
    List<Course> showAllCourse();
    void addCourse(Scanner scanner);
    void updateCourse(Scanner scanner);
    void deleteCourse(Scanner scanner);
    List<Course> listCourseByName(Scanner scanner);

}
