package backend.drivers;

import backend.dao.concretes.SubjectDaoImpl;
import backend.models.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectUI {
    private Logger log = LoggerFactory.getLogger(SubjectDaoImpl.class);
    private JTextField subjectName;
    private JLabel subjectNameLabel;
    private JTextField numberOfCredits;
    private JLabel creditsLabel;
    private JTextField courseID;
    private JLabel courseLabel;
    private JButton addSubjectButton;
    private JPanel SubjectUI;
    private JButton clearButton;
    private JButton deleteButton;
    private JTextField subjectID;
    private JButton viewButton;

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
                SubjectDaoImpl subjectDao  = new SubjectDaoImpl();
                subjectDao.addSubject(subject);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                subjectID.setText("");
                subjectName.setText("");
                numberOfCredits.setText("");
                courseID.setText("");
                String message = "Fields cleared";
                log.info(message);
            }
        });

        /**
         * to delete given id
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                int subject = Integer.parseInt(subjectID.getText());
                SubjectDaoImpl subjectDao  = new SubjectDaoImpl();
                subjectDao.deleteSubject(subject);
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

    /**
     * use a nested class to keep following two methods in separately. it will increase the readability also.
     */
    static class SubjectTable{
        public SubjectTable() {
        }

        /**
         * create the JTable
         * @return JTable
         */
        JTable creteTable(){
            String[] columns = {"subjectID","subjectName","numberOfCredit","courseID "};
            SubjectDaoImpl subjectDao  = new SubjectDaoImpl();
            String[][] rows = subjectDao.getAllSubjects();
            return new JTable(rows, columns);
        }

        /**
         * used to set created table
         */
        void setTable(){
            JFrame jFrame = new JFrame("subject ui");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTable jTable = creteTable();
            JScrollPane jScrollPane = new JScrollPane(jTable);
            jFrame.getContentPane().add(jScrollPane);
            jFrame.pack();
            jFrame.setVisible(true);
        }
    }
}
