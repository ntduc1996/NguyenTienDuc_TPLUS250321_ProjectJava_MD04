import presentation.admin.AdminMenu;
import presentation.student.StudentMenu;
import validate.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("============ HỆ THỐNG QUẢN LÝ ĐÀO TẠO ============");
            System.out.println("1. Đăng nhập với tư cách quản trị viên");
            System.out.println("2. Đăng nhập với tư cách Học viên");
            System.out.println("3. Thoát");
            System.out.println("=====================================================");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        AdminMenu adminMenu = new AdminMenu();
                        if (adminMenu.showLogin(scanner)) {
                            adminMenu.displayAdminMenu(scanner);
                        }
                        break;
                    case 2:
                        StudentMenu studentMenu = new StudentMenu();
                        if(studentMenu.showLoginStudent(scanner)) {
                            studentMenu.displayStudentMenu(scanner);
                        }
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Mời chọn mục (1-3)");
                }
            }

        } while (true);
    }
}
