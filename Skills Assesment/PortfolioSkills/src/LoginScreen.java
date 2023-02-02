import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class LoginScreen{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnExit;
    private JButton btnLogin;
    private JButton btnNewUser;
    public JPanel mainPanel;
    private Utils utils = new Utils();

    JFrame frame;
    Logger newLog = Logger.getLogger(LoginScreen.class.getName());
    /**
     * Constructor for this window, containing any required setup.
     */
    public LoginScreen() {
        newLog.info("Opening Login Screen");
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation(500, 400);
        frame.setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateLogin();
            }
        });
        btnNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CreateNewUserScreen user = new CreateNewUserScreen();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Checks the text of the username and password fields to see if the corresponding user exists
     */
    private void validateLogin(){
        newLog.info("Validating login.");
        String usernameString = txtUsername.getText();
        String passwordString = utils.passwordToString(txtPassword.getPassword());
        if (usernameString.isEmpty() || passwordString.isEmpty()){
            utils.displayMessage("Username or password cannot be blank.");
        }
        else{
            UserClass existingUser = new UserClass(usernameString, passwordString);
            String[] returnedInfo = existingUser.checkLoginInfo();
            boolean usernameTrue = false;
            if (returnedInfo[0].equals(existingUser.username)){
                usernameTrue = true;
            }
            boolean passwordTrue = false;
            if (returnedInfo[1].equals(existingUser.password)){
                passwordTrue = true;
            }
            if (usernameTrue && passwordTrue){
                //username and password exist in database
                //time to login
                frame.setVisible(false);
                newLog.info("Login Successful.");
                MainMenuScreen mainMenuScreen = new MainMenuScreen();
            }
            else{
                utils.displayMessage("Username or Password is incorrect.");
            }
        }
    }

}
