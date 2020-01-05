package backend.drivers;

import backend.dao.concretes.StudentDaoImpl;
import backend.dao.concretes.SubjectDaoImpl;
import backend.dao.interfaces.StudentDao;
import backend.dao.interfaces.SubjectDao;
import backend.models.Student;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentUI {
    private JTextField email;
    private JTextField mobile;
    private JTextField subjectID;
    private JTextField studentID;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField city;
    private JTextField street;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton editSubmitButton;
    private JButton viewButton;
    private JPanel student;

    public StudentUI() {
        /**
         * to take data from user interface
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName1 = firstName.getText();
                String lastName1  = lastName.getText();
                String city1      = city.getText();
                String street1    = street.getText();
                String email1     = email.getText();
                String mobile1    = mobile.getText();
                Student student = new Student(firstName1,lastName1,city1,street1,email1,mobile1);
                StudentDao studentDao = new StudentDaoImpl();
                studentDao.addStudent(student);
            }
        });

        /**
         * clear fields
         */
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstName.setText("");
                lastName.setText("");
                city.setText("");
                street.setText("");
                email.setText("");
                mobile.setText("");
                subjectID.setText("");
                studentID.setText("");
            }
        });

        /**
         * to take data and view
         */
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /**
         * to view data
         */
        viewButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                StudentTable studentTable = new StudentTable();
                studentTable.setTable();
            }
        });

        /**
         * to edit data
         */
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentId1 = Integer.parseInt(studentID.getText());
                StudentDao studentDao = new StudentDaoImpl();
                Student student = (Student) studentDao.getOneStudent(studentId1);
                studentID.setText(student.getStudentId());
                firstName.setText(student.getFirstName());
                lastName.setText(student.getLastName());
                city.setText(student.getCity());
                street.setText(student.getStreet());
                email.setText(student.getEmail());
                mobile.setText(student.getMobile());
            }
        });

        /**
         * to edit
         */
        editSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId1 = String.valueOf(studentID.getText());
                String firstName1 = firstName.getText();
                String lastName1 = lastName.getText();
                String city1 = city.getText();
                String street1 = street.getText();
                String email1 = email.getText();
                String mobile1 = mobile.getText();
                Student student = new Student(firstName1, lastName1, city1, street1, email1, mobile1);
                student.setStudentId(studentId1);
                StudentDao studentDao = new StudentDaoImpl();
                studentDao.updateStudent(student);
            }
        });

        /**
         * to delete
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentId = Integer.parseInt(studentID.getText());
                StudentDao studentDao = new StudentDaoImpl();
                studentDao.deleteStudent(studentId);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentUI");
        frame.setContentPane(new StudentUI().student);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    static class StudentTable{
        public StudentTable() {
        }

        /**
         * create the JTable
         * @return JTable
         */
        JTable creteTable(){
            String[] columns = {"studentID","firstName","lastName","city","street","email","mobile"};
            StudentDao studentDao = new StudentDaoImpl();
            String[][] rows = studentDao.getAllStudents();
            return new JTable(rows, columns);
        }

        /**
         * used to set created table
         */
        void setTable(){
            JFrame jFrame = new JFrame("subject");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTable jTable = creteTable();
            JScrollPane jScrollPane = new JScrollPane(jTable);
            jFrame.getContentPane().add(jScrollPane);
            jFrame.pack();
            jFrame.setVisible(true);
        }
    }
}
