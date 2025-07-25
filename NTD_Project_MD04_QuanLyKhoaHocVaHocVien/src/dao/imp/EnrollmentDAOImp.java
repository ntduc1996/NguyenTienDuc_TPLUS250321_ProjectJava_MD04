package dao.imp;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import model.*;
import model.entity.Enrollment;
import ulti.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {
    private final CourseDAO courseDAO;

    public EnrollmentDAOImp() {
        courseDAO = new CourseDAOImp();
    }

    @Override
    public boolean addEnrollment(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean success = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call register_course(?,?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            callSt.registerOutParameter(3, Types.BIT);
            callSt.execute();
            success = callSt.getInt(3) == 1;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return success;
    }

    @Override
    public List<CourseEnrollment> getEnrollmentByStudentId(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<CourseEnrollment> listEnroll = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_enrolled_courses_by_student(?)}");
            callSt.setInt(1, studentId);
            ResultSet rs = callSt.executeQuery();
            listEnroll = new ArrayList<>();
            while (rs.next()) {
                CourseEnrollment courseEnrollment = new CourseEnrollment();
                courseEnrollment.setCourseId(rs.getInt("c.course_id"));
                courseEnrollment.setCourseName(rs.getString("c.course_name"));
                courseEnrollment.setInstructor(rs.getString("c.course_instructor"));
                courseEnrollment.seteRegisterAt(rs.getDate("e.register_at").toLocalDate());
                courseEnrollment.seteStatus(Constants.status.valueOf(rs.getString("e.status")));
                listEnroll.add(courseEnrollment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEnroll;
    }

    @Override
    public boolean studentCancelEnrollment(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call cancel_enrollment_by_student(?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            int result = callSt.executeUpdate();
            if (result > 0) {
                return true;
            }
//        result  trả ra số bản ghi bị thay đổi trong database, nên nếu không có bản ghi nào thay đổi thì kq trả về false
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<CombineCES> listStudentByCourse() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<CombineCES> listCombine = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_list_student_by_course()}");
            ResultSet rs = callSt.executeQuery();
            listCombine = new ArrayList<>();
            while (rs.next()) {
                CombineCES combineCES = new CombineCES();
                combineCES.setEnrollmentId(rs.getInt("e.enrollment_id"));
                combineCES.setCourseId(rs.getInt("e.course_id"));
                combineCES.setStudentId(rs.getInt("e.student_id"));
                combineCES.setCourseName(rs.getString("c.course_name"));
                combineCES.setStudentName(rs.getString("s.student_name"));
                combineCES.setStatus(Constants.status.valueOf(rs.getString("e.status")));
                listCombine.add(combineCES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCombine;
    }

    @Override
    public Enrollment isEnrollmentExist(int enrollmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Enrollment enrollment = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_enrollment_id_exist(?)}");
            callSt.setInt(1, enrollmentId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                enrollment = new Enrollment();
                enrollment.setId(rs.getInt("enrollment_id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setRegisteredDate(rs.getTimestamp("register_at").toLocalDateTime());
                enrollment.setStatus(Constants.status.valueOf(rs.getString("status")));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollment;
    }


    @Override
    public boolean confirmStudentEnrollment(int enrollmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call confirm_student_to_enrollment(?)}");
            callSt.setInt(1, enrollmentId);
            int result = callSt.executeUpdate();
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
    public boolean deniedStudentEnrollment(int enrollmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call denied_student_to_enrollment(?)}");
            callSt.setInt(1, enrollmentId);
            int result = callSt.executeUpdate();
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

}
