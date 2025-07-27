package presentation.admin;

import business.ICourseServices;
import business.imp.CourseServices;
import model.entity.Course;
import presentation.color.Color;
import validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CourseManagerMenu {
    private final ICourseServices courseServices;

    public CourseManagerMenu() {
        courseServices = new CourseServices();
    }

    public void displayCourseManagerMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm khóa học theo tên");
            System.out.println("6. Sắp xếp theo tên hoặc id");
            System.out.println("7. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice) ) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        makeColor();
                        courseServices.showAllCourse().forEach(System.out::print);
                        System.out.println("-".repeat(121));
                        break;
                    case 2:
                        courseServices.addCourse(scanner);
                        break;
                    case 3:
                        courseServices.updateCourse(scanner);
                        break;
                    case 4:
                        courseServices.deleteCourse(scanner);
                        break;
                    case 5:
                        List<Course> courses = courseServices.listCourseByName(scanner);
                        if(courses.isEmpty()) {
                            System.out.println(Color.ANSI_RED_BACKGROUND+"Không có tên khóa học phù hợp"+Color.ANSI_RESET);
                        }else {
                            makeColor();
                           courses.forEach(System.out::println);
                        }
                        break;
                    case 6:
                        boolean returnMenu = true;
                        List<Course> courseList = courseServices.showAllCourse();
                        do {
                            System.out.println("Chọn cách sắp xếp: ");
                            System.out.println("1. Sắp xếp tăng dần theo tên");
                            System.out.println("2. Sắp xếp giảm dần theo tên");
                            System.out.println("3. Sắp xếp tăng dần theo id");
                            System.out.println("4. Sắp xếp giảm dần theo id");
                            System.out.println("5. Quay lại ");
                            System.out.print("Lựa chọn của bạn: ");
                            int choice2 = Integer.parseInt(scanner.nextLine());
                            switch (choice2) {
                                case 1:
                                    makeColor();
                                    courseList.stream().sorted(Comparator.comparing(Course::getCourseName)).forEach(System.out::println);
                                    break;
                                case 2:
                                    makeColor();
                                    courseList.stream().sorted(Comparator.comparing(Course::getCourseName).reversed()).forEach(System.out::println);
                                    break;
                                case 3:
                                    makeColor();
                                    courseList.stream().sorted(Comparator.comparing(Course::getId)).forEach(System.out::println);
                                    break;
                                case 4:
                                    makeColor();
                                    courseList.stream().sorted(Comparator.comparing(Course::getId).reversed()).forEach(System.out::println);
                                    break;
                                case 5:
                                    returnMenu = false;
                                    break;
                                default:
                                    System.out.println("Mời chọn các mục 1-5");
                            }
                        } while (returnMenu);
                        break;
                    case 7:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-7)");
                }
            }
        }while (isExit) ;
    }

    private static void makeColor() {
        System.out.println("-".repeat(121));
        System.out.print(Color.ANSI_BLUE_BACKGROUND);
        System.out.printf("|%-5s|%-50s|%-15s|%-30s|%-15s|","STT","Tên khóa học","Thời lượng", "Giáo viên","Ngày tạo");
        System.out.println(Color.ANSI_RESET);
        System.out.println("-".repeat(121));
    }
}

