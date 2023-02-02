import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.logging.Logger;

public class CreateNewUserScreen {
    public JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtRetypePassword;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JComboBox cmbxUserType;
    private JTextField txtEmail;
    private JButton btnCancel;
    private JButton btnSave;
    private JDatePicker dateDOB;
    private JDatePicker dateCreateDate;
    JFrame frame;
    private Utils utils = new Utils();
    Logger newLog = Logger.getLogger(LoginScreen.class.getName());

    /**
     * Constructor for this window, containing any required setup.
     */
    public CreateNewUserScreen(){

        newLog.info("Beginning new user creation process.");
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocation(500, 400);
        frame.setVisible(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateFields();
            }
        });
        cmbxUserType.addItem("None");
        cmbxUserType.addItem("Standard");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                LoginScreen loginScreen = new LoginScreen();
            }
        });
    }

    /**check all current fields
     *if any are null, user will need to re enter them
     *if all are valid, create a new UserClass which then saves to the database
     *
     */
    private void validateFields(){
        newLog.info("Beginning new user validation...");
        boolean allValid = true;
        JTextField[] fields = {txtUsername, txtFirstName, txtLastName, txtEmail};
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getText().isEmpty()) {
                allValid = false;
            }
        }
        //putting after loop so to not spam the user
        if (allValid == false){
            utils.displayMessage("Please ensure all text fields have a value.");
        }
        if (txtPassword.getPassword().length == 0
        || txtRetypePassword.getPassword().length == 0){
            allValid = false;
            utils.displayMessage("Please ensure passwords are not blank.");
        }
        else if (comparePasswords() == false){
            allValid = false;
            utils.displayMessage("The Passwords do not match.");
        }
        if (cmbxUserType.getSelectedIndex() == 0){
            allValid = false;
            utils.displayMessage("User Type must not be set to None.");
        }


        if (dateDOB.getModel().getValue() == null) {
            allValid = false;
            utils.displayMessage("DOB cannot be blank.");
        }
        if (dateCreateDate.getModel().getValue() == null){
            allValid = false;
            utils.displayMessage("Create Date cannot be blank.");
        }

        //if all are valid, time to create a new user
        //else, prompt user to double check values
        if (allValid == true){
            newLog.info("All fields valid. Creating user & adding to database...");
            Date sqlDateDOB = utils.convertToDateSQL(this.dateDOB.getModel());
            Date sqlDateCreate = utils.convertToDateSQL(this.dateCreateDate.getModel());
            UserClass newUser = new UserClass(txtUsername.getText(),
                    passwordToString(txtPassword.getPassword()),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    sqlDateDOB,
                    cmbxUserType.getSelectedItem().toString(),
                    txtEmail.getText(),
                    sqlDateCreate
                    );
            newUser.addUserToDatabase();
            utils.displayMessage("New User created.");
            frame.setVisible(false);
            MainMenuScreen mainMenuScreen = new MainMenuScreen();
        }
    }

    /**Compare the two password fields against each other to see if they match
     * Assumes the length of neither is < 0
     * @return True if the passwords match
     */
    private boolean comparePasswords(){
        newLog.info("Validating password...");
        boolean valid = true;
        char[] originalPass = txtPassword.getPassword();
        char[] dupePass = txtRetypePassword.getPassword();
        for (int i = 0; i < originalPass.length; i++){
            if (originalPass[i] != dupePass[i]){
                valid = false;
            }
        }
        return valid;
    }

    /**Convert char[] password to String for database purposes
     *
     * @param password The char[] to convert
     * @return The output String
     */
    private String passwordToString(char[] password){
        String fullString = "";
        for (int i = 0; i < password.length; i++){
            fullString += password[i];
        }
        return fullString;
    }



}
