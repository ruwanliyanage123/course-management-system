package backend.drivers;

import backend.dao.concretes.CourseDaoImpl;
import backend.dao.interfaces.CourseDao;
import backend.database.DatabaseConnection;
import backend.models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseUI {
    private JPanel courseUI;
    private JButton addCourseButton;
    private JTextField courseNameField;
    private JTextField result;
    private static Logger logger = LoggerFactory.getLogger(CourseUI.class);
    private CourseDao courseDao;


    public CourseUI() {
        this.courseDao = new CourseDaoImpl();
        /**
         * Action for the course adding button.
         */
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                Course course = new Course(courseName);
                courseDao.addCourse(course);
                courseNameField.setText("");
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("CourseUI");
        frame.setContentPane(new CourseUI().courseUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,1500);
        frame.pack();
        frame.setVisible(true);
    }
}
