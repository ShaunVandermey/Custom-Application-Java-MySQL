import javastack.JavaStack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackScreen {
    private JTextField txtToAddToStack;
    private JButton btnAddToStack;
    private JButton btnShowAllStack;
    private JButton btnReadFromStack;
    private JPanel mainPanel;
    private JButton btnExit;
    JFrame frame;
    JavaStack javaStack;

    /**
     * Constructor for this window, containing any required setup.
     */
    public StackScreen(){
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocation(500, 400);
        frame.setVisible(true);
        javaStack = new JavaStack();
        btnAddToStack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javaStack.showPush(txtToAddToStack.getText());
                txtToAddToStack.setText("");
                JOptionPane.showMessageDialog(null, "Added to stack.");
            }
        });
        btnReadFromStack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javaStack.showPopOne();
            }
        });
        btnShowAllStack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javaStack.showPopAll();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuScreen mainMenuScreen = new MainMenuScreen();
            }
        });
    }
}
