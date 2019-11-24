package backend.drivers;

import backend.dao.concretes.InstructorDaoImpl;
import backend.dao.interfaces.InstructorDao;
import backend.models.Instructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorUI {
    private JPanel instructor;
    private JTextField nic;
    private JTextField practicalHours;
    private JTextField street;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField city;
    private JTextField mobile;
    private JTextField email;
    private JTextField salary;
    private JButton addButton;
    private JButton viewButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton editSubmitButton;
    private JButton clearButton;

    public InstructorUI() {

        /**
         * to add a instructor
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
                int practicalHours1 = Integer.parseInt(practicalHours.getText());
                Instructor instructor = new Instructor(nic1,firstName1, lastName1, city1, street1, email1, mobile1, salary1);
                instructor.setWorkingHours(practicalHours1);
                InstructorDao instructorDao = new InstructorDaoImpl();
                instructorDao.addInstructor(instructor);
            }
        });
    }
}
