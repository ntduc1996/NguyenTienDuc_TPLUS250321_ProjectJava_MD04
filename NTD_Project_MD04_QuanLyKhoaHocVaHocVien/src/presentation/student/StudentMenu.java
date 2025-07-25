package presentation.student;

import business.ICourseServices;
import business.IEnrollmentServices;
import business.IStudentServices;
import business.imp.CourseServices;
import business.imp.EnrollmentServices;
import business.imp.StudentServices;
import dao.CourseDAO;
import dao.imp.CourseDAOImp;
import model.*;
import model.entity.Course;
import model.entity.Student;
import validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StudentMenu {
    private final IStudentServices studentServices;
    private final ICourseServices courseServices;
    private final IEnrollmentServices enrollmentServices;

    public StudentMenu() {
        enrollmentServices = new EnrollmentServices();
        courseServices = new CourseServices();
        studentServices = new StudentServices();
    }

    public boolean showLoginStudent(Scanner scanner) {
        String email;
        System.out.print("Nhập email: ");
        do {
            email = "a.nguyen@example.com";
        } while (!Validator.inputNotEmpty(email));

        String password;
        System.out.print("Nhập password: ");
        do {
            password = "PassWord";
        } while (!Validator.inputNotEmpty(password));

        Student student = studentServices.loginStudent(email, password);
        if (!Objects.isNull(student)) {
            System.out.println("Đăng nhập thành công! Xin chào ");
            // Chuyển đến menu chính
            return true;
        } else {
            System.out.println("Đăng nhập thất bại!");
            return false;
        }
    }

    public void displayStudentMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("============ MENU HỌC VIÊN ============");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Hủy đăng ký nếu chưa bắt đầu");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("====================================");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        System.out.println("1. Hiển thị tất cả danh sách khóa học");
                        System.out.println("2. Tìm các khóa học theo tên");
                        String input = scanner.nextLine().trim();
                        if ("1".equals(input)) {
                            System.out.printf("|%-5s|%-55s|%-10s|%-30s|%-15s|\n", "STT", "Tên khóa học", "Thời lượng", "Giáo viên", "Ngày tạo");
                            courseServices.showAllCourse().forEach(System.out::println);
                        } else if ("2".equals(input)) {
                            CourseDAO courseDAO = new CourseDAOImp();
                            System.out.println("Nhập tên khóa học muốn tìm");
                            String courseName = scanner.nextLine();
                            List<Course> listByName = courseDAO.findCourseByName(courseName);
                            listByName.forEach(System.out::println);
                        } else {
                            System.out.println("Mời chọn 1 trong 2 cách hiển thị danh sách khóa học");
                        }
                        break;
                    case 2:
                        enrollmentServices.addEnrollmentByStudent(scanner);
                        break;
                    case 3:
                        do {
                            System.out.println("Chọn cách hiển thị");
                            System.out.println("1. Khóa học sắp xếp theo tên tăng dần");
                            System.out.println("2. Khóa học sắp xếp theo tên giảm dần");
                            System.out.println("3. Khóa học sắp xếp theo ngày đăng ký tăng dần");
                            System.out.println("4. Khóa học sắp xếp theo ngày đăng ký giảm dần");
                            String inputChoice = scanner.nextLine();
                            if ("1".equals(inputChoice)) {
                                enrollmentServices.getEnrollmentByStudent().stream()
                                        .sorted(Comparator.comparing(CourseEnrollment::getCourseName))
                                        .toList().forEach(System.out::println);
                                break;
                            } else if ("2".equals(inputChoice)) {
                                enrollmentServices.getEnrollmentByStudent().stream()
                                        .sorted(Comparator.comparing(CourseEnrollment::getCourseName).reversed())
                                        .toList().forEach(System.out::println);
                                break;
                            } else if ("3".equals(inputChoice)) {
                                enrollmentServices.getEnrollmentByStudent().stream()
                                        .sorted(Comparator.comparing(CourseEnrollment::geteRegisterAt))
                                        .toList().forEach(System.out::println);
                                break;
                            } else if ("4".equals(inputChoice)) {
                                enrollmentServices.getEnrollmentByStudent().stream()
                                        .sorted(Comparator.comparing(CourseEnrollment::geteRegisterAt).reversed())
                                        .toList().forEach(System.out::println);
                                break;
                            } else {
                                System.out.println("Mời chọn lại (1-4)");
                            }
                        } while (true);

                        break;
                    case 4:
                        enrollmentServices.cancelEnrollmentByStudent(scanner);
                        break;
                    case 5:
                        studentServices.changePassword(scanner);
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-6)");
                }
            }

        } while (isExit);
    }
}
