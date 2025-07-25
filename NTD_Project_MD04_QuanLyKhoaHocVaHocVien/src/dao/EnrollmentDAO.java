package dao;

import model.CombineCES;
import model.CourseEnrollment;
import model.entity.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    boolean addEnrollment(int courseId, int studentId);
    List<CourseEnrollment> getEnrollmentByStudentId(int studentId);
    boolean studentCancelEnrollment(int studentId, int courseId);
    List<CombineCES>listStudentByCourse();
    Enrollment isEnrollmentExist(int enrollmentId);
    boolean confirmStudentEnrollment(int enrollmentId);
    boolean deniedStudentEnrollment(int enrollmentId);

}
