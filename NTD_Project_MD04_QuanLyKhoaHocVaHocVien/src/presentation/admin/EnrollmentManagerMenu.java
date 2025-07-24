package presentation.admin;

import validate.Validator;

import java.util.Scanner;

public class EnrollmentManagerMenu {
    public void enrollmentManagerMenu(Scanner scanner) {

        boolean isExit = true;
        do {
            System.out.println("1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Thêm học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-4)");
                }
            }
        } while (isExit) ;
    }
}
