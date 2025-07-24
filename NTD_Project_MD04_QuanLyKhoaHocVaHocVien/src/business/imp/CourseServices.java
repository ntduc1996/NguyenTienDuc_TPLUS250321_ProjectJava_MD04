package business.imp;

import business.ICourseServices;
import dao.CourseDAO;
import dao.imp.CourseDAOImp;
import model.Course;
import presentation.color.Color;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CourseServices implements ICourseServices {
    private final CourseDAO courseDAO;

    public CourseServices() {
        courseDAO = new CourseDAOImp();
    }

    @Override
    public List<Course> showAllCourse() {
        return courseDAO.showAllCourse();
    }

    @Override
    public void addCourse(Scanner scanner) {
        Course course = new Course();
        // Nhập tên khóa học và kiểm tra trùng
        String name;
        do {
            System.out.println("Nhập vào tên khóa học:");
            name = scanner.nextLine();
            if (!Validator.inputNotEmpty(name)) {
                continue;
            }
            if (courseDAO.isCourseNameExist(name)) {
                System.err.println(Color.ANSI_RED_BACKGROUND + "Tên khóa học đã tồn tại. Nhập lại!" + Color.ANSI_RESET);
                continue;
            }
            break;
        } while (true);
        course.setCourseName(name);

        // Nhập thời lượng
        String durationStr;
        do {
            System.out.println("Nhập vào thời lượng khóa học:");
            durationStr = scanner.nextLine();
        } while (!Validator.inputIsInteger(durationStr));
        course.setDuration(Integer.parseInt(durationStr));

        // Nhập giáo viên
        String instructor;
        do {
            System.out.println("Nhập vào tên giáo viên:");
            instructor = scanner.nextLine();
        } while (!Validator.inputNotEmpty(instructor));
        course.setInstructor(instructor);

        // Gán ngày tạo
        course.setDate(java.time.LocalDate.now());
        if (courseDAO.addNewCourse(course)) {
            System.out.println(Color.ANSI_GREEN_BACKGROUND + "Thêm khóa học mới thành công" + Color.ANSI_RESET);
        } else {
            System.err.println(Color.ANSI_RED_BACKGROUND + "Thêm mới thất bại" + Color.ANSI_RESET);
        }
    }

    @Override
    public void updateCourse(Scanner scanner) {
        Course course = getCourse(scanner);

        boolean isNotExit = true;
        do {
            System.out.println("1. Cập nhật tên khóa học");
            System.out.println("2. Cập nhật thời lượng khóa học");
            System.out.println("3. Cập nhật giáo viên");
            System.out.println("4. Thoát");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        System.out.println("Tên khóa học mới: ");
                        String name;
                        do {
                            name = scanner.nextLine();
                            course.setCourseName(name);
                        } while (!Validator.inputNotEmpty(name));
                        break;
                    case 2:
                        System.out.println("Thời lượng cập nhật: ");
                        String duration;
                        do {
                            duration = scanner.nextLine();
                            try {
                                course.setDuration(Integer.parseInt(duration));
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        } while (!Validator.inputIsInteger(duration));
                        break;
                    case 3:
                        System.out.println("Giáo viên mới: ");
                        String instructor;
                        do {
                            instructor = scanner.nextLine();
                            course.setInstructor(instructor);
                        } while (!Validator.inputNotEmpty(instructor));
                        break;
                    case 4:
                        isNotExit = false;
                        break;
                    default:
                        System.err.println("Chọn các mục (1-4)");
                }
            }
        } while (isNotExit);

        if (courseDAO.updateCourse(course)) {
            System.out.println(Color.ANSI_GREEN_BACKGROUND + "Cập nhật thành công" + Color.ANSI_RESET);
        }

    }

    private Course getCourse(Scanner scanner) {
        Course course;
        String input;
        do {
            System.out.println("Nhập vào mã khóa học cần thao tác:");
            input = scanner.nextLine();

            if (!Validator.inputIsInteger(input)) {
                course = null; // bắt buộc gán null để lặp lại
                continue;
            }

            int id = Integer.parseInt(input);
            course = courseDAO.findCourseById(id);

            if (course == null) {
                System.out.println(Color.ANSI_RED_BACKGROUND+"Mã khóa học không tồn tại, vui lòng nhập lại"+Color.ANSI_RESET);
            }

        } while (course == null);

        return course;
    }

    @Override
    public void deleteCourse(Scanner scanner) {
        Course course = getCourse(scanner);
        System.out.println(Color.ANSI_YELLOW + "Bạn có xác nhận xóa khóa học (Y|N)?" + Color.ANSI_RESET);
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            courseDAO.deleteCourse(course.getId());
            System.out.println(Color.ANSI_GREEN_BACKGROUND + "Đã xóa thành công" + Color.ANSI_RESET);
        }
    }

    @Override
    public List<Course> listCourseByName(Scanner scanner) {
        System.out.println("Nhập tên khóa học muốn tìm: ");
        String name = scanner.nextLine();
        return courseDAO.findCourseByName(name);
    }

}
