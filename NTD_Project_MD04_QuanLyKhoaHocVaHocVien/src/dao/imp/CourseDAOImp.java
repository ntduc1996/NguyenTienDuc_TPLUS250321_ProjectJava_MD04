package dao.imp;

import dao.CourseDAO;
import model.entity.Course;
import ulti.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO {
    @Override
    public List<Course> showAllCourse() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> listCourse = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_course()}");
            ResultSet rs = callSt.executeQuery();
            listCourse = new ArrayList<>();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("course_duration"));
                course.setInstructor(rs.getString("course_instructor"));
                course.setDate(rs.getDate("create_at").toLocalDate());
                listCourse.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCourse;
    }

    @Override
    public boolean addNewCourse(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_new_course(?,?,?,?)}");
            callSt.setString(1, course.getCourseName());
            callSt.setInt(2, course.getDuration());
            callSt.setString(3, course.getInstructor());
            callSt.setDate(4, Date.valueOf(LocalDate.now()));
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
    public Course findCourseById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_course_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("course_duration"));
                course.setInstructor(rs.getString("course_instructor"));
                course.setDate(rs.getDate("create_at").toLocalDate());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public boolean isCourseNameExist(String courseName) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call course_name_exist(?,?)}");
            callSt.setString(1, courseName);
            callSt.registerOutParameter(2,Types.INTEGER);
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
    public boolean updateCourse(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_course(?,?,?,?)}");
            callSt.setInt(1, course.getId());
            callSt.setString(2, course.getCourseName());
            callSt.setInt(3, course.getDuration());
            callSt.setString(4, course.getInstructor());
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
    public boolean deleteCourse(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_course(?)}");
            callSt.setInt(1,id);
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
    public List<Course> findCourseByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> listCourse = null;
        try {
            listCourse = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_course_by_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
               Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("course_duration"));
                course.setInstructor(rs.getString("course_instructor"));
                course.setDate(rs.getDate("create_at").toLocalDate());
                listCourse.add(course);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCourse;
    }

}
