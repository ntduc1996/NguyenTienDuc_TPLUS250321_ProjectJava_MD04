package business.imp;

import business.IStatisticServices;
import dao.StatisticsDAO;
import dao.imp.StatisticDAOImp;
import model.statistic.CourseStudentCount;
import presentation.color.Color;

import java.util.Comparator;
import java.util.List;

public class StatisticServices implements IStatisticServices {
    private final StatisticsDAO statisticsDAO;

    public StatisticServices() {
        statisticsDAO = new StatisticDAOImp();
    }

    @Override
    public void showTotalStudentsAndCourses() {

        System.out.println("-".repeat(62));
        System.out.print(Color.ANSI_PURPLE);
        System.out.printf("|%-30s|%-30s|\n", "Tổng số học viên","Tổng số khóa học");
        System.out.print(Color.ANSI_RESET);
        System.out.println("-".repeat(62));
        System.out.println(statisticsDAO.getTotalStudentsAndCourses());
        System.out.println("-".repeat(62));
    }

    @Override
    public void showStudentCountPerCourse() {
        List<CourseStudentCount> studentCounts = statisticsDAO.getStudentCountPerCourse();
        System.out.println("-".repeat(109));

        System.out.print(Color.ANSI_PURPLE_BACKGROUND);
        System.out.printf("|%-20s|%-55s|%-30s|", "Mã khóa học", "Tên khóa học", "Số học viên");
        System.out.println(Color.ANSI_RESET);
        System.out.println("-".repeat(109));
        studentCounts.forEach(System.out::println);
        System.out.println("-".repeat(109));
    }

    @Override
    public void showTop5Courses() {
        List<CourseStudentCount> studentCounts = statisticsDAO.getStudentCountPerCourse();
        System.out.println("-".repeat(109));

        System.out.print(Color.ANSI_PURPLE_BACKGROUND);
        System.out.printf("|%-20s|%-55s|%-30s|", "Mã khóa học", "Tên khóa học", "Số học viên");
        System.out.println(Color.ANSI_RESET);

        System.out.println("-".repeat(109));
        studentCounts.stream().sorted(Comparator.comparing(CourseStudentCount::getStudentCount).reversed()).limit(5).forEach(System.out::println);
        System.out.println("-".repeat(109));

    }

    @Override
    public void showCourseHaveMoreThan10Students() {
        List<CourseStudentCount> studentCounts = statisticsDAO.getStudentCountPerCourse();
        System.out.println("-".repeat(109));

        System.out.print(Color.ANSI_PURPLE_BACKGROUND);
        System.out.printf("|%-20s|%-55s|%-30s|", "Mã khóa học", "Tên khóa học", "Số học viên");
        System.out.println(Color.ANSI_RESET);

        System.out.println("-".repeat(109));
        studentCounts.stream().filter(s -> s.getStudentCount() > 10).forEach(System.out::println);
        System.out.println("-".repeat(109));
    }
}
