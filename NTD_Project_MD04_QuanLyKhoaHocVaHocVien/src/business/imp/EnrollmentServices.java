package business.imp;

import business.IEnrollmentServices;
import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import dao.imp.CourseDAOImp;
import dao.imp.EnrollmentDAOImp;
import dao.imp.StudentDAOImp;
import model.*;
import model.entity.Course;
import model.entity.Enrollment;
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
                System.out.println(Color.ANSI_RED_BACKGROUND + "Mã khóa học không tồn tại." + Color.ANSI_RESET);
            } else {
                break;
            }
        }

// Gọi DAO để thêm đăng ký
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        boolean success = enrollmentDAO.addEnrollment(Session.currentStudent.getId(), courseId);

        if (success) {
            System.out.println(Color.ANSI_GREEN_BACKGROUND + "Đăng ký thành công." + Color.ANSI_RESET);
        } else {
            System.out.println(Color.ANSI_YELLOW + "Khóa học đã được đăng ký. Vui lòng chọn khóa học khác" + Color.ANSI_RESET);
        }
    }

    @Override
    public List<CourseEnrollment> getEnrollmentByStudent() {
        int studentId = Session.currentStudent.getId();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        return enrollmentDAO.getEnrollmentByStudentId(studentId);
    }

    @Override
    public void cancelEnrollmentByStudent(Scanner scanner) {
        int studentId = Session.currentStudent.getId();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        System.out.println("Nhập mã khóa học muốn hủy đăng ký");
        String inputCourseId = scanner.nextLine();
        if (Validator.inputIsInteger(inputCourseId)) {
            Course course = courseDAO.findCourseById(Integer.parseInt(inputCourseId));
            if (course == null) {
                System.out.println("Mã khóa học không tồn tại");
            } else {
                boolean result = enrollmentDAO.studentCancelEnrollment(studentId, course.getId());
                if (result) {
                    System.out.println("Hủy khóa học thành công");
                } else {
                    System.out.println("Học viên chưa đăng ký khóa học này!");
                }
            }
        }
    }

    @Override
    public List<CombineCES> listStudentByCourse() {
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        return enrollmentDAO.listStudentByCourse();
    }

    @Override
    public void confirmStudentEnrollment(Scanner scanner) {
        String enrollmentId;
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        do {
            System.out.println("Nhập vào mã đăng ký khóa học");
            enrollmentId = scanner.nextLine();
            Enrollment enrollmentCheck = enrollmentDAO.isEnrollmentExist(Integer.parseInt(enrollmentId));
            if (enrollmentCheck == null) {
                System.out.println("Mã đăng ký không tồn tại");
            } else {
                boolean result = enrollmentDAO.confirmStudentEnrollment(Integer.parseInt(enrollmentId));
                if (result) {
                    System.out.println("Thêm học viên thành công");
                    break;
                }else {
                    System.out.println("Thêm học viên thất bại");
                }
            }
        } while (true);


    }

    @Override
    public void deniedStudentEnrollment(Scanner scanner) {
        String enrollmentId;
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImp();
        do {
            System.out.println("Nhập vào mã đăng ký khóa học");
            enrollmentId = scanner.nextLine();
            Enrollment enrollmentCheck = enrollmentDAO.isEnrollmentExist(Integer.parseInt(enrollmentId));
            if (enrollmentCheck == null) {
                System.out.println("Mã đăng ký không tồn tại");
            } else {
                boolean result = enrollmentDAO.deniedStudentEnrollment(Integer.parseInt(enrollmentId));
                if (result) {
                    System.out.println("Xóa học viên thành công");
                    break;
                }else {
                    System.out.println("Xóa học viên thất bại");
                }
            }
        } while (true);
    }
}
