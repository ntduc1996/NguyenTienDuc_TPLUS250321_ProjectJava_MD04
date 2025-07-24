package model;

import dao.CourseDAO;
import dao.imp.CourseDAOImp;
import presentation.admin.CourseManagerMenu;
import presentation.color.Color;
import validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Course {

    private int id;
    private String courseName;
    private int duration;
    private String instructor;
    private LocalDate createdAt;

    public Course() {
    }

    public Course(int id, String courseName, int duration, String instructor) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
        this.instructor = instructor;
        this.createdAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getDate() {
        return createdAt;
    }

    public void setDate(LocalDate date) {
        this.createdAt = date;
    }

    @Override
    public String toString() {
        return String.format("|%-5d|%-55s|%-10d|%-30s|%-15s|\n", this.id, this.courseName, this.duration, this.instructor, this.createdAt.toString());
    }

//    public void inputCourseData(Scanner scanner) {
//        this.courseName = createCourseName(scanner);
//        this.duration = createCourseDuration(scanner);
//        this.instructor = createCourseInstructor(scanner);
//        this.createdAt = LocalDate.now();
//    }
//    private String createCourseName(Scanner scanner) {
//        System.out.println("Nhập vào tên khóa học: ");
//        String courseName;
//        do {
//           courseName = scanner.nextLine();
//            if (!Validator.inputNotEmpty(courseName)) {
//                continue;
//            }
//            if(courseDAO.isCourseNameExist(courseName)) {
//                System.out.println(Color.ANSI_RED_BACKGROUND +"Tên khóa học đã tồn tại. Vui lòng nhập lại"+ Color.ANSI_RESET);
//                continue;
//            }
//            break;
//        }while (true);
//        return courseName;
//    }
//
//    private int createCourseDuration(Scanner scanner) {
//        System.out.println("Nhập vào thời lượng khóa học: ");
//        do {
//            String courseDuration = scanner.nextLine();
//            if (Validator.inputIsInteger(courseDuration)) {
//                return Integer.parseInt(courseDuration);
//            }
//        }while (true);
//    }
//
//    private String createCourseInstructor(Scanner scanner) {
//        System.out.println("Nhập vào tên giáo viên: ");
//        do {
//            String courseInstructor = scanner.nextLine();
//            if (Validator.inputNotEmpty(courseInstructor)) {
//                return courseInstructor;
//            }
//        }while (true);
//    }

}
