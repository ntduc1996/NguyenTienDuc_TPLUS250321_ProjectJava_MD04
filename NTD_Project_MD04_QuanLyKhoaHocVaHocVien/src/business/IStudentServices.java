package business;

import model.Student;

import java.util.List;
import java.util.Scanner;

public interface IStudentServices {
    Student loginStudent(String email, String password);
    List<Student> allStudentList();
    void addStudent(Scanner scanner);
    void updateStudent(Scanner scanner);
    void deleteStudent(Scanner scanner);
    List<Student> findStudentAdvanced(String searchType,String keyword);
    void changePassword(Scanner scanner);
}
