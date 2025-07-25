package presentation.admin;

import business.IAdminServices;
import business.imp.AdminServices;
import model.entity.Admin;
import validate.Validator;

import java.util.Objects;
import java.util.Scanner;

public class AdminMenu {
    private final IAdminServices adminServices;

    public AdminMenu() {
        adminServices = new AdminServices();
    }
    public boolean showLogin(Scanner scanner) {
        String username;
        System.out.print("Nhập username: ");
        do {
            username = "admin01";
        } while (!Validator.inputNotEmpty(username));
        String password;
        System.out.print("Nhập password: ");
        do {
            password = "adminpass123";
        } while (!Validator.inputNotEmpty(password));
        Admin admin = adminServices.loginAdmin(username, password);

        if (!Objects.isNull(admin)) {
            System.out.println("Đăng nhập thành công! Xin chào ");
            // Chuyển đến menu chính
            return true;
        } else {
            System.out.println("Đăng nhập thất bại!");
            return false;
        }
    }
    public void displayAdminMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("============ MENU ADMIN ============");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký học");
            System.out.println("4. Thống kê học viên theo khóa học");
            System.out.println("5. Thoát");
            System.out.println("====================================");
            System.out.print("Nhập lựa chọn: ");
            String  choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)){
                switch (Integer.parseInt(choice)) {
                    case 1:
                        CourseManagerMenu courseManagerMenu = new CourseManagerMenu();
                        courseManagerMenu.displayCourseManagerMenu(scanner);
                        break;
                    case 2:
                        StudentManagerMenu studentManagerMenu = new StudentManagerMenu();
                        studentManagerMenu.displayStudentManagerMenu(scanner);
                        break;
                    case 3:
                        EnrollmentManagerMenu enrollmentManagerMenu = new EnrollmentManagerMenu();
                        enrollmentManagerMenu.enrollmentManagerMenu(scanner);
                        break;
                    case 4:
                        StatisticManager statisticManagerMenu = new StatisticManager();
                        statisticManagerMenu.statisticManagerMenu(scanner);
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-3)");
                }
            }

        } while (isExit);
    }
}
