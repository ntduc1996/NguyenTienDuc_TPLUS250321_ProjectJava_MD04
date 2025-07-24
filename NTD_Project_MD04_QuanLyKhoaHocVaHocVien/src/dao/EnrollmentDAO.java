package dao;

import model.CourseEnrollment;
import model.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    boolean addEnrollment(int courseId, int studentId);
    List<CourseEnrollment> getEnrollmentByStudentId(int studentId);

}
