package presentation.admin;

import business.IEnrollmentServices;
import business.imp.EnrollmentServices;
import model.CombineCES;
import validate.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class EnrollmentManagerMenu {
    private final IEnrollmentServices enrollmentService;

    public EnrollmentManagerMenu() {
        enrollmentService = new EnrollmentServices();
    }

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
                        enrollmentService.listStudentByCourse().stream()
                                .sorted(Comparator.comparing(CombineCES::getCourseId))
                                .toList().forEach(System.out::println);
                        break;
                    case 2:
                        enrollmentService.confirmStudentEnrollment(scanner);
                        break;
                    case 3:
                        enrollmentService.deniedStudentEnrollment(scanner);
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
