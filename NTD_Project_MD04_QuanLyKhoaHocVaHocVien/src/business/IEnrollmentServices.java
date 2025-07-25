package business;

import model.CombineCES;
import model.CourseEnrollment;

import java.util.List;
import java.util.Scanner;

public interface IEnrollmentServices {
    void addEnrollmentByStudent(Scanner scanner);
    List<CourseEnrollment> getEnrollmentByStudent();
    void cancelEnrollmentByStudent(Scanner scanner);
    List<CombineCES>listStudentByCourse();
    void confirmStudentEnrollment(Scanner scanner);
    void deniedStudentEnrollment(Scanner scanner);
}
