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
                Lecture lecture = new Lecture(nic1, firstName1, lastName1, city1, street1, email1,
                        mobile1, salary1, lecturerHours1);
                LectureDao lectureDao = new LectureDaoImpl();
                lectureDao.addLecture(lecture);
            }
        });

        /**
         * clear fields
         */
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nic.setText("");
                firstName.setText("");
                lastName.setText("");
                mobile.setText("");
                email.setText("");
                salary.setText("");
                city.setText("");
                street.setText("");
                lecturerHours.setText("");
            }
        });

        /**
         * see the lecturer
         */
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecturerTable lecturerTable = new LecturerTable();
                lecturerTable.setTable();
            }
        });

        /**
         * edit
         */
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nic1 = nic.getText();
                LectureDao lectureDao = new LectureDaoImpl();
                Lecture lecture  = (Lecture) lectureDao.getOneLecture(nic1);
                nic.setText(lecture.getNic());
                firstName.setText(lecture.getFirstName());
                lastName.setText(lecture.getLastName());
                mobile.setText(lecture.getMobile());
                email.setText(lecture.getEmail());
                salary.setText(String.valueOf(lecture.getSalary()));
                city.setText(lecture.getCity());
                street.setText(lecture.getStreet());
                lecturerHours.setText(String.valueOf(lecture.getWorkingHours()));
            }
        });

        /**
         * to keep changes
         */
        editSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nic1 = nic.getText();
                String firstName1 = firstName.getText();
                String lastName1 = lastName.getText();
                String city1 = city.getText();
                String street1 = street.getText();
                String email1 = email.getText();
                String mobile1 = mobile.getText();
                double salary1 = Double.parseDouble(salary.getText());
                int lecturerHours1 = Integer.parseInt(lecturerHours.getText());
                Lecture lecture = new Lecture(nic1, firstName1, lastName1, city1, street1, email1,
                        mobile1, salary1, lecturerHours1);
                LectureDao lectureDao = new LectureDaoImpl();
                lectureDao.updateLecture(lecture);
            }
        });

        /**
         * to delete
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                String nic1 = nic.getText();
                LectureDao lectureDao = new LectureDaoImpl();
                lectureDao.deleteLecture(nic1);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LecturerUI");
        frame.setContentPane(new LecturerUI().lecturer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    static class LecturerTable {
        public LecturerTable() {
        }

        /**
         * create the JTable
         *
         * @return JTable
         */
        JTable creteTable() {
            String[] columns = { "nic", "firstName", "lastName", "mobile", "email", "salary",
                    "city", "street", "lecturerHours" };
            LectureDao lectureDao = new LectureDaoImpl();
            String[][] rows = lectureDao.getAllLectures();
            return new JTable(rows, columns);
        }

        /**
         * used to set created table
         */
        void setTable() {
            JFrame jFrame = new JFrame("lecturer");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTable jTable = creteTable();
            JScrollPane jScrollPane = new JScrollPane(jTable);
            jFrame.getContentPane().add(jScrollPane);
            jFrame.pack();
            jFrame.setVisible(true);
        }
    }
}
