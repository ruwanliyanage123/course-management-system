package backend.drivers;

import backend.dao.concretes.CourseDaoImpl;
import backend.dao.interfaces.CourseDao;
import backend.database.DatabaseConnection;
import backend.models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class CourseUI {
    private static Object BorderLayout;
    private JPanel courseUI;
    private JButton addButton;
    private JTextField courseNameField;
    private JTextField courseId;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton editButton;
    private JButton submitButton;
    private static Logger logger = LoggerFactory.getLogger(CourseUI.class);
    private CourseDao courseDao;
    static JTable jTable;

    public CourseUI() {
        this.courseDao = new CourseDaoImpl();

        /**
         * to display the all courses table
         */

        /**
         * Action for the course adding button.
         */
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                Course course = new Course(courseName);
                courseDao.addCourse(course);
                courseNameField.setText("");
            }
        });

        /**
         * Action for the course delete button
         */
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int courseID = Integer.parseInt(courseId.getText());
                courseDao.deleteCourse(courseID);
                courseId.setText("");
            }
        });

        /**
         * Action for the course search button
         */
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int courseID = Integer.parseInt(courseId.getText());
                Course course = (Course) courseDao.getOneCourse(courseID);
                courseNameField.setText(course.getCourseName());
                courseId.setEditable(false);
                courseNameField.setEditable(false);
            }
        });

        /**
         * Action for the course edit button
         */
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int courseID = Integer.parseInt(courseId.getText());
                Course course = (Course) courseDao.getOneCourse(courseID);
                courseNameField.setText(course.getCourseName());
            }
        });
        /**
         * Action for the course submit button
         */
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String editedCourseName = courseNameField.getText();
                int courseID = Integer.parseInt(courseId.getText());
                Course course = new Course(editedCourseName);
                courseDao.updateCourse(course, courseID);
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
