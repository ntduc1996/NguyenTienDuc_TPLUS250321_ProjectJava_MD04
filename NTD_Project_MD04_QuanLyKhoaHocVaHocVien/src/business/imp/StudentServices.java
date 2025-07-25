package business.imp;

import business.IStudentServices;
import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import model.entity.Student;
import presentation.color.Color;
import ulti.Session;
import validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentServices implements IStudentServices {
    private final StudentDAO studentDAO;

    public StudentServices() {
        studentDAO = new StudentDAOImp();
    }

    @Override
    public Student loginStudent(String email, String password) {
        return studentDAO.loginStudent(email, password);
    }

    @Override
    public List<Student> allStudentList() {
        return studentDAO.allStudentList();
    }

    @Override
    public void addStudent(Scanner scanner) {
        Student student = new Student();
        String studentName;
        do {
            System.out.println("Nhập vào tên học viên:");
            studentName = scanner.nextLine();
        } while (!Validator.inputNotEmpty(studentName));
        student.setStudentName(studentName);

        // Nhập ngày tháng năm sinh
        LocalDate dob = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Nhập vào ngày tháng năm sinh theo định dạng yyyy-MM-dd");
        do {
            String dateOfBirth = scanner.nextLine();
            if (!Validator.inputNotEmpty(dateOfBirth)) {
                continue;
            }
            try {
                dob = LocalDate.parse(dateOfBirth, formatter);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Sai định dạng ngày, vui lòng nhập theo định dạng yyyy-MM-dd");
            }
        } while (dob == null);
        student.setDateOfBirth(dob);

        // Nhập email
        String email;
        do {
            System.out.println("Nhập vào email:");
            email = scanner.nextLine();
            if (!Validator.emailLegit(email)) {
                continue;
            }
            if (studentDAO.isStudentEmailExist(email)) {
                System.out.println(Color.ANSI_RED_BACKGROUND + "Email đã tồn tại, vui lòng tạo email khác" + Color.ANSI_RESET);
                continue;
            }
            break;
        } while (true);
        student.setEmail(email);

        // Gán giới tính
        boolean gender;
        do {
            System.out.println("Nhập vào giới tính của học viên: ");
            String studentSex = scanner.nextLine();
            if (!Validator.inputNotEmpty(studentSex)) continue;
            if (studentSex.equalsIgnoreCase("Nam")) {
                gender = true;
                break;
            } else if (studentSex.equalsIgnoreCase("Nữ")) {
                gender = false;
                break;
            } else {
                System.out.println(Color.ANSI_PURPLE + "Giới tính chỉ có Nam hoặc Nữ, vui lòng nhập lại" + Color.ANSI_RESET);
            }
        } while (true);
        student.setSex(gender);
        // gán số điện thoại
        String phone;
        do {
            System.out.println("Nhập vào số điện thoại (gồm 10 số theo định dạng số di động Việt Nam), có thể bỏ trống: ");
            phone = scanner.nextLine();
            if (Validator.inputIsPhoneNumber(phone)) {
                break;
            }
        } while (true);
        student.setPhone(phone);
        // gán mật khẩu
        String password;
        do {
            System.out.println("Nhập vào mật khẩu (có ít nhất 6 ký tự, có ít nhât 1 chữ hoa và 1 chữ thường, không bao gồm số hoặc ký tự đặc biệt): ");
            password = scanner.nextLine();
            if (Validator.passwordLegit(password)) {
                break;
            }
        } while (true);
        student.setPassword(password);
        // gán ngày tạo
        student.setCreatedAt(java.time.LocalDate.now());
        if (studentDAO.addStudent(student)) {
            System.out.println(Color.ANSI_GREEN_BACKGROUND + "Thêm mới học viên thành công" + Color.ANSI_RESET);
        } else {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Thêm mới thất bại" + Color.ANSI_RESET);
        }
    }

    @Override
    public void updateStudent(Scanner scanner) {
        String studentId;
        Student student;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        do {
            System.out.println("Nhập vào mã học viên cần chỉnh sửa: ");
            studentId = scanner.nextLine();
            if (!Validator.inputIsInteger(studentId)) {
                student = null;
                continue;
            }
            int id = Integer.parseInt(studentId);
            student = studentDAO.getStudentById(id);
            if (student == null) {
                System.out.println(Color.ANSI_RED_BACKGROUND + "Mã học viên không tồn tại, vui lòng nhập lại" + Color.ANSI_RESET);
            }
        } while (student == null);
        boolean isExist = true;
        do {
            System.out.println("1. Cập nhật tên học viên");
            System.out.println("2. Cập nhật ngày tháng năm sinh");
            System.out.println("3. Cập nhật email");
            System.out.println("4. Cập nhật giới tính");
            System.out.println("5. Cập nhật số điện thoại");
            System.out.println("6. Cập nhật mật khẩu");
            System.out.println("7. Thoát");
            String choice = scanner.nextLine();
            if (Validator.inputIsInteger(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        System.out.println("Nhập vào tên học viên: ");
                        String studentName;
                        do {
                            studentName = scanner.nextLine();
                            student.setStudentName(studentName);
                        } while (!Validator.inputNotEmpty(studentName));
                        break;
                    case 2:
                        System.out.println("Nhập vào ngày tháng năm sinh (yyyy-MM-dd): ");
                        String dob;
                        do {
                            dob = scanner.nextLine();
                            try {
                                LocalDate dobDate = LocalDate.parse(dob, formatter);
                                student.setDateOfBirth(dobDate);
                            } catch (Exception e) {
                                System.out.println(Color.ANSI_RED + "Ngày nhập vào chưa đúng định dạng, vui lòng nhập lại" + Color.ANSI_RESET);
                                dob = null;
                            }
                        } while (!Validator.inputNotEmpty(dob));
                        break;
                    case 3:
                        System.out.println("Nhập vào email mới: ");
                        String email;
                        do {
                            email = scanner.nextLine().trim();
                            if (studentDAO.isStudentEmailExist(email)) {
                                continue;
                            }
                            if (!Validator.emailLegit(email)) {
                                continue;
                            }
                            student.setEmail(email);
                            break;
                        } while (true);
                        break;
                    case 4:
                        System.out.println(" Xác nhận thay đổi giới tính (Y|N)? ");
                        String genderChange = scanner.nextLine();
                        if (genderChange.equalsIgnoreCase("Y")) {
                            student.setSex(!student.isSex());
                            System.out.println(Color.ANSI_PURPLE + "Thay đổi giới tính thành công" + Color.ANSI_RESET);
                        } else {
                            System.out.println(Color.ANSI_YELLOW + "Giới tính vẫn giữ nguyên" + Color.ANSI_RESET);
                        }
                        break;
                    case 5:
                        System.out.println("Nhập vào số điện thoại mới: ");
                        String phoneNumber;
                        do {
                            phoneNumber = scanner.nextLine();
                            if (!Validator.inputIsPhoneNumber(phoneNumber)) {
                                continue;
                            }
                            student.setPhone(phoneNumber);
                            break;
                        } while (true);
                        break;
                    case 6:
                        System.out.println("Nhập vào mật khẩu mới: ");
                        String newPassword;
                        while (true) {
                            newPassword = scanner.nextLine();
                            if (!Validator.passwordLegit(newPassword)) {
                                continue;
                            }
                            student.setPassword(newPassword);
                            break;
                        }
                        break;
                    case 7:
                        isExist = false;
                        break;
                    default:
                        System.out.println(Color.ANSI_YELLOW_BACKGROUND + "Mời chọn mục (1-7)" + Color.ANSI_RESET);
                }
            }
        } while (isExist);
        if (studentDAO.updateStudent(student)) {
            System.out.println(Color.ANSI_GREEN + "Cập nhật thành công" + Color.ANSI_RESET);
        } else {
            System.out.println(Color.ANSI_RED + "Cập nhật thất bại" + Color.ANSI_RESET);
        }
    }

    @Override
    public void deleteStudent(Scanner scanner) {
        String studentId;
        Student student;
        do {
            System.out.println("Nhập vào mã học viên cần xóa: ");
            studentId = scanner.nextLine();
            if (!Validator.inputIsInteger(studentId)) {
                student = null;
                continue;
            }
            int id = Integer.parseInt(studentId);
            student = studentDAO.getStudentById(id);
            if (student == null) {
                System.out.println(Color.ANSI_RED_BACKGROUND + "Mã học viên không tồn tại, vui lòng nhập lại" + Color.ANSI_RESET);
            }
        } while (student == null);
        System.out.printf("Bạn có xác nhận muốn xóa học viên %s có mã số là %d không?(Y|N)\n", student.getStudentName(), student.getId());
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            studentDAO.deleteStudent(Integer.parseInt(studentId));
            System.out.println(Color.ANSI_GREEN_BACKGROUND + "Đã xóa thành công" + Color.ANSI_RESET);
        } else {
            System.out.println(Color.ANSI_CYAN + "Không xóa học viên" + Color.ANSI_RESET);
        }

    }

    @Override
    public List<Student> findStudentAdvanced(String searchType, String keyword) {
        return studentDAO.findStudentAdvanced(searchType, keyword);
    }

    @Override
    public void changePassword(Scanner scanner) {
        int studentId = Session.currentStudent.getId();
        Student student = studentDAO.getStudentById(studentId);
        String newPassword;
        do {
            System.out.println("Nhập vào mật khẩu mới");
             newPassword = scanner.nextLine();
        }while (!Validator.passwordLegit(newPassword));
        boolean flag = true;
        do {
            System.out.println("Chọn hình thức xác thực: ");
            System.out.println("1. Xác thực bằng email");
            System.out.println("2. Xác thực bẳng số điện thoại và mật khẩu cũ");
            System.out.println("3. Thoát chỉnh sửa");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (!Validator.inputIsInteger(choice)) {
                continue;
                }
            switch (Integer.parseInt(choice)) {
                case 1:
                    System.out.println("Nhập vào email của bạn để xác thực");
                    String email = scanner.nextLine();
                    if (student.getEmail().equals(email)) {
                        student.setPassword(newPassword);
                        boolean result = studentDAO.updateStudent(student);
                        System.out.println(result?"Đổi mật khẩu thành công" : "Đổi mật khẩu thất bại");
                        flag = false;
                    } else {
                        System.out.println("Email không đúng, không thể thay đổi mật khẩu");
                    }
                    break;
                case 2:
                    System.out.println("Nhập vào số điện thoại: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Nhập vào mật khẩu cũ: ");
                    String oldPassword = scanner.nextLine();
                    if (phoneNumber.equals(student.getPhone()) && oldPassword.equals(student.getPassword())) {
                        student.setPassword(newPassword);
                        boolean result = studentDAO.updateStudent(student);
                        System.out.println(result?"Đổi mật khẩu thành công" : "Đổi mật khẩu thất bại");
                        flag = false;
                    } else {
                        System.out.println("Số điện thoại hoặc mật khẩu cũ không chính xác, không thể thay đổi mật khẩu");
                    }
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Mời chọn các mục (1-3)");
            }
        } while (flag);


    }


}
