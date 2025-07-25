package presentation.admin;

import business.IStatisticServices;
import business.imp.StatisticServices;
import dao.StatisticsDAO;
import validate.Validator;

import java.util.Scanner;

public class StatisticManager {
    private final IStatisticServices statisticsServices;

    public StatisticManager() {
        statisticsServices = new StatisticServices();
    }

    public void statisticManagerMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("1. Thống kê theo số lượng khóa học và học viên");
            System.out.println("2. Thống kê học viên theo từng khóa học");
            System.out.println("3. Top 5 khóa học đông học viên nhất");
            System.out.println("4. Liệt kê khóa học có trên 10 học viên");
            System.out.println("5. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        statisticsServices.showTotalStudentsAndCourses();
                        break;
                    case 2:
                        statisticsServices.showStudentCountPerCourse();
                        break;
                    case 3:
                        statisticsServices.showTop5Courses();
                        break;
                    case 4:
                        statisticsServices.showCourseHaveMoreThan10Students();
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-5)");
                }
            }
        } while (isExit) ;
    }
}
