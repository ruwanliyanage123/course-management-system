package backend.drivers;

import backend.dao.concretes.StudentDaoImpl;
import backend.dao.interfaces.StudentDao;
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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentUI");
        frame.setContentPane(new StudentUI().student);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
