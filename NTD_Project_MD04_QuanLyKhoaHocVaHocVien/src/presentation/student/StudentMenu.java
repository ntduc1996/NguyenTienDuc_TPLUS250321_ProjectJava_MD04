package presentation.student;

import business.ICourseServices;
import business.IEnrollmentServices;
import business.IStudentServices;
import business.imp.CourseServices;
import business.imp.EnrollmentServices;
import business.imp.StudentServices;
import dao.EnrollmentDAO;
import dao.imp.EnrollmentDAOImp;
import model.Admin;
import model.Student;
import presentation.color.Color;
import validate.Validator;

import java.util.Objects;
import java.util.Scanner;

public class StudentMenu {
    private final IStudentServices studentServices;
    private final ICourseServices courseServices;

    public StudentMenu() {
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
    public void  displayStudentMenu(Scanner scanner) {
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
                        System.out.printf("|%-5s|%-55s|%-10s|%-30s|%-15s|\n","STT","Tên khóa học","Thời lượng", "Giáo viên","Ngày tạo");
                        courseServices.showAllCourse().forEach(System.out::println);
                        break;
                    case 2:
                        IEnrollmentServices enrollmentServices = new EnrollmentServices();
                        enrollmentServices.addEnrollmentByStudent(scanner);
                        break;
                    case 3:
                        IEnrollmentServices enrollmentServices2 = new EnrollmentServices();
                        enrollmentServices2.getEnrollmentByStudent().forEach(System.out::println);
                        break;
                    case 4:
                        break;
                    case 5:
                        studentServices.changePassword(scanner);
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-6)");
                }}

        } while (isExit);
    }
}
