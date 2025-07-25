package dao;

import model.entity.Student;

import java.util.List;

public interface StudentDAO {
    Student loginStudent(String email, String password);
    List<Student> allStudentList();
    boolean isStudentEmailExist(String email);
    Student getStudentById(int studentId);
    List<Student> findStudentAdvanced(String searchType, String keyword);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int studentId);
}
