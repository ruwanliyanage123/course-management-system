package backend.drivers;

import javax.swing.*;

public class StudentUI {
    private JPanel studentUI;

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentUI");
        frame.setContentPane(new StudentUI().studentUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
