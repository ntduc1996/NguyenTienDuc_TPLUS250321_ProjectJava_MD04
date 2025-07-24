package dao.imp;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import model.Constants;
import model.Course;
import model.CourseEnrollment;
import model.Enrollment;
import ulti.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {
    private final CourseDAO courseDAO;

    public EnrollmentDAOImp() {
        courseDAO = new CourseDAOImp();
    }

    @Override
    public boolean addEnrollment( int studentId,int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean success = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call register_course(?,?,?)}");
            callSt.setInt(1, studentId);
            callSt.setInt(2, courseId);
            callSt.registerOutParameter(3,Types.BIT );
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
}
