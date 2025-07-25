package dao.imp;

import dao.StatisticsDAO;
import model.entity.Course;
import model.statistic.CourseStudentCount;
import model.statistic.TotalStatistics;
import ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAOImp implements StatisticsDAO {
    @Override
    public TotalStatistics getTotalStudentsAndCourses() {
        Connection conn = null;
        CallableStatement callSt = null;
        TotalStatistics  totalStatistics = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_total_students_courses(?,?)}");

            callSt.registerOutParameter(1, Types.INTEGER);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int totalStudents = callSt.getInt(1);
            int totalCourses = callSt.getInt(2);
            totalStatistics = new TotalStatistics(totalStudents, totalCourses);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalStatistics;
    }

    @Override
    public List<CourseStudentCount> getStudentCountPerCourse() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<CourseStudentCount> listStudentsPerCourse = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_students_per_course()}");
            ResultSet rs = callSt.executeQuery();
            listStudentsPerCourse = new ArrayList<>();
            while (rs.next()) {
                CourseStudentCount courseStudentCount = new CourseStudentCount();
                courseStudentCount.setCourseId(rs.getInt("c.course_id"));
                courseStudentCount.setCourseName(rs.getString("c.course_name"));
                courseStudentCount.setStudentCount(rs.getInt("total_students"));
                listStudentsPerCourse.add(courseStudentCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudentsPerCourse;
    }


}
