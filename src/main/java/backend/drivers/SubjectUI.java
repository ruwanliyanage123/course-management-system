package backend.drivers;

import backend.dao.concretes.SubjectDaoImpl;
import backend.models.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectUI {
    private JTextField subjectName;
    private JLabel subjectNameLabel;
    private JTextField numberOfCredits;
    private JLabel creditsLabel;
    private JTextField courseID;
    private JLabel courseLabel;
    private JButton addSubjectButton;
    private JPanel SubjectUI;

    public SubjectUI() {
        /**
         * Take data from user interface and put into database
         */
        addSubjectButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                String subjectName1 = subjectName.getText();
                int numberOfCredits1 = Integer.parseInt(numberOfCredits.getText());
                int courseID1 = Integer.parseInt(courseID.getText());
                Subject subject = new Subject(subjectName1, numberOfCredits1, courseID1);
                SubjectDaoImpl subjectDao = new SubjectDaoImpl();
                subjectDao.addSubject(subject);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SubjectUI");
        frame.setContentPane(new SubjectUI().SubjectUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
