package dao.imp;

import dao.StudentDAO;
import model.Admin;
import model.Course;
import model.Student;
import presentation.color.Color;
import ulti.ConnectionDB;
import ulti.Session;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public Student loginStudent(String email, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call login_student(?,?,?)}");
            callSt.setString(1, email);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(3);
            if (callSt.wasNull()) {
                System.out.println(Color.ANSI_RED +"Đăng nhập thất bại, không tìm thấy học viên"+Color.ANSI_RESET);
            }else {
                Student student = getStudentById(result);
                Session.currentStudent = student;
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return null;
    }

    @Override
    public List<Student> allStudentList() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudent = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call show_all_student()}");
            ResultSet rs = callSt.executeQuery();
            listStudent = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setDateOfBirth(rs.getDate("student_dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreatedAt(rs.getDate("create_at").toLocalDate());
                listStudent.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudent;
    }

    @Override
    public boolean isStudentEmailExist(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_email_exist(?,?)}");
            callSt.setString(1, email);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(2);
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Student getStudentById(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_id(?)}");
            callSt.setInt(1, studentId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
               student = new Student();
               student.setId(rs.getInt("student_id"));
               student.setStudentName(rs.getString("student_name"));
               student.setDateOfBirth(rs.getDate("student_dob").toLocalDate());
               student.setEmail(rs.getString("email"));
               student.setSex(rs.getBoolean("sex"));
               student.setPhone(rs.getString("phone"));
               student.setPassword(rs.getString("password"));
               student.setCreatedAt(rs.getDate("create_at").toLocalDate());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public List<Student> findStudentAdvanced(String searchType, String keyword) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> studentList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_student_advanced(?,?)}");
            callSt.setString(1,searchType);
            callSt.setString(2,keyword);
            ResultSet rs = callSt.executeQuery();
            studentList = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setDateOfBirth(rs.getDate("student_dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreatedAt(rs.getDate("create_at").toLocalDate());
                studentList.add(student);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return studentList;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_new_student(?,?,?,?,?,?,?)}");
            callSt.setString(1, student.getStudentName());
            callSt.setDate(2, Date.valueOf(student.getDateOfBirth()));
            callSt.setString(3, student.getEmail());
            callSt.setBoolean(4, student.isSex());
            callSt.setString(5, student.getPhone());
            callSt.setString(6, student.getPassword());
            callSt.setDate(7, Date.valueOf(LocalDate.now()));
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_student(?,?,?,?,?,?,?)}");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getStudentName());
            callSt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            callSt.setString(4, student.getEmail());
            callSt.setBoolean(5, student.isSex());
            callSt.setString(6, student.getPhone());
            callSt.setString(7, student.getPassword());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_student(?)}");
            callSt.setInt(1,studentId);
            callSt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
