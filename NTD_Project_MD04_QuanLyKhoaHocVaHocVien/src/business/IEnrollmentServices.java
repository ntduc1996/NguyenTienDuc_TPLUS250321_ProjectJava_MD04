package business;

import model.CourseEnrollment;
import model.Enrollment;

import java.util.List;
import java.util.Scanner;

public interface IEnrollmentServices {
    void addEnrollmentByStudent(Scanner scanner);
    List<CourseEnrollment> getEnrollmentByStudent();
}
