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

        /**
         * to clear fields
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
                practicalHours.setText("");
            }
        });

        /**
         * view data
         */
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructorTable instructorTable = new InstructorTable();
                instructorTable.setTable();
            }
        });

        /**
         * edit instructor
         */
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nic1 = nic.getText();
                InstructorDao instructorDao = new InstructorDaoImpl();
                Instructor instructor = (Instructor)instructorDao.getOneInstructor(nic1);
                nic.setText(instructor.getNic());
                firstName.setText(instructor.getFirstName());
                lastName.setText(instructor.getLastName());
                mobile.setText(instructor.getMobile());
                email.setText(instructor.getEmail());
                salary.setText(String.valueOf(instructor.getSalary()));
                city.setText(instructor.getCity());
                street.setText(instructor.getStreet());
                practicalHours.setText(String.valueOf(instructor.getWorkingHours()));
            }
        });

        /**
         * edit
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
                int lecturerHours1 = Integer.parseInt(practicalHours.getText());
                Instructor instructor = new Instructor(nic1,firstName1, lastName1, city1, street1, email1, mobile1, salary1);
                InstructorDao instructorDao = new InstructorDaoImpl();
                instructorDao.updateInstructor(instructor);
            }
        });

        /**
         * delete
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nic1 = nic.getText();
                InstructorDao instructorDao = new InstructorDaoImpl();
                instructorDao.deleteInstructor(nic1);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InstructorUI");
        frame.setContentPane(new InstructorUI().instructor);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    static class InstructorTable {
        public InstructorTable() {
        }

        /**
         * create the JTable
         *
         * @return JTable
         */
        JTable creteTable() {
            String[] columns = { "nic", "firstName", "lastName", "mobile", "email", "salary",
                    "city", "street", "practicalHours" };
            InstructorDao instructorDao = new InstructorDaoImpl();
            String[][] rows = instructorDao.getAllInstructors();
            return new JTable(rows, columns);
        }

        /**
         * used to set created table
         */
        void setTable() {
            JFrame jFrame = new JFrame("instructor");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTable jTable = creteTable();
            JScrollPane jScrollPane = new JScrollPane(jTable);
            jFrame.getContentPane().add(jScrollPane);
            jFrame.pack();
            jFrame.setVisible(true);
        }
    }
}
