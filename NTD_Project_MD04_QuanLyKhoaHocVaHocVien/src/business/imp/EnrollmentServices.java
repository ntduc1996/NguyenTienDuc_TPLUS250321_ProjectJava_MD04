package business.imp;

import business.IEnrollmentServices;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import dao.imp.CourseDAOImp;
import dao.imp.EnrollmentDAOImp;
import dao.imp.StudentDAOImp;
import model.Course;
import model.CourseEnrollment;
import model.Enrollment;
import model.Student;
import presentation.color.Color;
import ulti.Session;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class EnrollmentServices implements IEnrollmentServices {
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;

    public EnrollmentServices() {
        studentDAO = new StudentDAOImp();
        courseDAO = new CourseDAOImp();
    }

    @Override
    public void addEnrollmentByStudent(Scanner scanner) {
        Course course;
        int courseId;
        while (true) {
            System.out.print("Nhập vào mã khóa học muốn đăng ký: ");
            String inputCourseId = scanner.nextLine();

            if (!Validator.inputIsInteger(inputCourseId)) {
                continue;
            }

            courseId = Integer.parseInt(inputCourseId);
            course = courseDAO.findCourseById(courseId);

            if (course == null) {
                System.out.println(Color.ANSI_RED_BACKGROUND+"Mã khóa học không tồn tại."+Color.ANSI_RESET);
            } else {
                break;
            }
        }

// Gọi DAO để thêm đăng ký
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        boolean success = enrollmentDAO.addEnrollment(Session.currentStudent.getId(), courseId);

        if (success) {
            System.out.println(Color.ANSI_GREEN_BACKGROUND+"Đăng ký thành công."+Color.ANSI_RESET);
        } else {
            System.out.println(Color.ANSI_YELLOW+"Khóa học đã được đăng ký. Vui lòng chọn khóa học khác"+Color.ANSI_RESET);
        }
    }

    @Override
    public List<CourseEnrollment> getEnrollmentByStudent() {
        int studentId = Session.currentStudent.getId();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        return enrollmentDAO.getEnrollmentByStudentId(studentId);
    }
}
