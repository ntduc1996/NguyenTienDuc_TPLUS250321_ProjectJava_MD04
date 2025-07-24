package presentation.admin;

import business.IStudentServices;
import business.imp.StudentServices;
import model.Student;
import validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManagerMenu {
    private final IStudentServices studentServices;

    public StudentManagerMenu() {
        studentServices = new StudentServices();
    }

    public void displayStudentManagerMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Tìm khóa học theo tên, email hoặc Id ");
            System.out.println("6. Sắp xếp theo tên hoặc id");
            System.out.println("7. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        studentServices.allStudentList().forEach(System.out::println);
                        break;
                    case 2:
                        studentServices.addStudent(scanner);
                        break;
                    case 3:
                        studentServices.updateStudent(scanner);
                        break;
                    case 4:
                        studentServices.deleteStudent(scanner);
                        break;
                    case 5:
                        boolean returnMenu = true;
                        do {
                            System.out.println("Chọn cách tìm: ");
                            System.out.println("1. Tìm theo tên");
                            System.out.println("2. Tìm theo id");
                            System.out.println("3. Tìm theo email");
                            System.out.println("4. Quay lại");
                            System.out.print("Lựa chọn của bạn: ");
                            String choice2 = scanner.nextLine();
                            if (Validator.inputIsInteger(choice2)) {
                                switch (Integer.parseInt(choice2)) {
                                    case 1:
                                        System.out.println("Nhập vào tên cần tìm: ");
                                        String keyword = scanner.nextLine();
                                        List<Student> listByName = studentServices.findStudentAdvanced("name", keyword);
                                        listByName.forEach(System.out::println);
                                        break;
                                    case 2:
                                        System.out.println("Nhập vào mã học viên cần tìm: ");
                                        String keyword2 = scanner.nextLine();
                                        List<Student> listById = studentServices.findStudentAdvanced("id", keyword2);
                                        listById.forEach(System.out::println);
                                        break;
                                    case 3:
                                        System.out.println("Nhập vào email cần tìm: ");
                                        String keyword3 = scanner.nextLine();
                                        List<Student> listByEmail = studentServices.findStudentAdvanced("email", keyword3);
                                        listByEmail.forEach(System.out::println);
                                        break;
                                    case 4:
                                        returnMenu = false;
                                        break;
                                    default:
                                        System.out.println("Mời chọn các mục 1-4");
                                }
                            }

                        } while (returnMenu);
                        break;
                    case 6:
                        boolean returnMenu2 = true;
                        do {
                            System.out.println("Chọn cách sắp xếp: ");
                            System.out.println("1. Sắp xếp tăng dần theo tên");
                            System.out.println("2. Sắp xếp tăng dần theo id");
                            System.out.println("3. Sắp xếp giảm dần theo tên");
                            System.out.println("4. Sắp xếp giảm dần theo id");
                            System.out.println("5. Quay lại ");
                            System.out.print("Lựa chọn của bạn: ");
                            String choice2 = scanner.nextLine();
                            if (Validator.inputIsInteger(choice2)) {
                                switch (Integer.parseInt(choice2)) {
                                    case 1:
                                        studentServices.allStudentList().stream().
                                                sorted(Comparator.comparing(Student::getStudentName)).toList()
                                                .forEach(System.out::println);
                                        break;
                                    case 2:
                                        studentServices.allStudentList().stream().
                                                sorted(Comparator.comparing(Student::getId)).toList().forEach(System.out::println);
                                        break;
                                    case 3:
                                        studentServices.allStudentList().stream().
                                                sorted(Comparator.comparing(Student::getStudentName).reversed()).toList()
                                                .forEach(System.out::println);
                                        break;
                                    case 4:
                                        studentServices.allStudentList().stream().
                                                sorted(Comparator.comparing(Student::getId).reversed()).toList()
                                                .forEach(System.out::println);
                                        break;
                                    case 5:
                                        returnMenu2 = false;
                                        break;
                                    default:
                                        System.out.println("Mời chọn các mục 1-5");
                                }
                            }

                        } while (returnMenu2);
                        break;
                    case 7:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Mời chọn mục (1-7)");
                }
            }
        } while (isExit);
    }
}
