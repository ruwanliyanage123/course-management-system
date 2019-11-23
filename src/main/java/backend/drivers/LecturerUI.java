package backend.drivers;

import backend.dao.concretes.LectureDaoImpl;
import backend.dao.interfaces.LectureDao;
import backend.models.Lecture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecturerUI {

    private JPanel lecturer;
    private JTextField nic;
    private JTextField lecturerHours;
    private JTextField firstName;
    private JTextField street;
    private JTextField lastName;
    private JTextField city;
    private JTextField mobile;
    private JTextField salary;
    private JTextField email;
    private JButton addButton;
    private JButton viewButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton editSubmitButton;
    private JButton clearButton;
    private JLabel firstNam;

    public LecturerUI() {

        /**
         * Take data from user interface and put into database
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nic1 = nic.getText();
                String firstName1 = firstName.getText();
                String lastName1 = lastName.getText();
                String mobile1 = mobile.getText();
                String email1 = email.getText();
                double salary1 = Double.parseDouble(salary.getText());
                String city1 = city.getText();
                String street1 = street.getText();
                int lecturerHours1 = Integer.parseInt(lecturerHours.getText());
                Lecture lecture = new Lecture(nic1,firstName1,lastName1,city1,street1,email1,mobile1,salary1,lecturerHours1);
                LectureDao lectureDao = new LectureDaoImpl();
                lectureDao.addLecture(lecture);
            }
        });
    }
}
