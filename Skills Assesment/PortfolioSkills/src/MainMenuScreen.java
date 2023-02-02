import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen {
    private JButton btnTextFileProcessing;
    private JButton btnSerialization;
    private JButton btnImageFileProcessing;
    private JButton btnDataStructures;
    private JButton btnTextEditor;
    private JButton btnEditDeleteUser;
    private JButton btnSearchUser;
    private JButton btnCreateUser;
    private JButton btnExit;
    private JPanel mainPanel;

    JFrame frame;

    /**
     * Constructor for this window, containing any required setup.
     * Main menu is fairly simple, not requiring any special functions.
     */
    public MainMenuScreen() {

        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(500, 400);
        frame.setVisible(true);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                LoginScreen loginScreen = new LoginScreen();
            }
        });
        btnTextFileProcessing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ReadWriteItems readWriteItems = new ReadWriteItems();
            }
        });
        btnSerialization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                SerializeDeserializeItems serializeDeserializeItems = new SerializeDeserializeItems();
            }
        });
        btnDataStructures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                StackScreen stackScreen = new StackScreen();
            }
        });
        btnImageFileProcessing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                FlagsGui flagsGui = new FlagsGui();
            }
        });
        btnEditDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                EditUserScreen editScreen = new EditUserScreen();
            }
        });
        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CreateNewUserScreen newUserScreen = new CreateNewUserScreen();
            }
        });
        btnSearchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                SearchUserScreen searchUserScreen = new SearchUserScreen();
            }
        });
        btnTextEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                TextEditor textEditor = new TextEditor();
            }
        });
    }
}
