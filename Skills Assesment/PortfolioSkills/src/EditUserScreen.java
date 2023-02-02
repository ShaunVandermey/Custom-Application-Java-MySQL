import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserScreen {
    private JTextField txtSearchEmail;
    private JTextField txtSearchID;
    private JButton btnSearchID;
    private JButton btnSearchByEmail;
    private JTextField txtUsername;
    private JTextField txtFirstName;
    private JPasswordField txtPassword;
    private JTextField txtLastName;
    private JTextField txtUserType;
    private JTextField txtEmail;
    private JButton btnExit;
    private JButton btnUpdate;
    private JTextField txtUserID;
    private JPanel mainPanel;
    private JTextField txtDateDOB;
    private JTextField txtDateCreateDate;
    private JButton btnDeleteCurrentUser;
    JFrame frame;
    Utils utils = new Utils();
    UserClass currentUser;

    /**
     * Constructor for this window, containing any required setup.
     */
    public EditUserScreen() {

        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocation(500, 400);
        frame.setVisible(true);
        btnSearchID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //user is searching by user ID
                int searchID;
                UserClass foundUser;
                if (!txtSearchID.getText().isEmpty()){
                    try{
                        searchID = Integer.parseInt(txtSearchID.getText());
                        DatabaseConnection connection = new DatabaseConnection();
                        foundUser = connection.getUser(searchID);
                        if (foundUser != null){
                            clearFields();
                            fillFieldsWithUser(foundUser);
                        }
                    } catch (NumberFormatException ex) {
                        utils.displayMessage("Could not convert the requested ID to integer.");
                    }
                }
                else{
                    utils.displayMessage("Search box must not be empty.");
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuScreen mainMenuScreen = new MainMenuScreen();
            }
        });
        btnSearchByEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //searching by email
                System.out.println("here");
                try{
                    DatabaseConnection connection = new DatabaseConnection();
                    UserClass user = connection.getUser(txtSearchEmail.getText());
                    if (user != null){
                        clearFields();
                    fillFieldsWithUser(user);
                    }
                    System.out.println("here");
                } catch (Exception ex) {
                    utils.displayMessage("Could not retrieve that email from the database.");
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserClass user = new UserClass(txtUsername.getText(),
                            utils.passwordToString(txtPassword.getPassword()),
                            txtFirstName.getText(),
                            txtLastName.getText(),
                            currentUser.getUserDOB(),
                            txtUserType.getText(),
                            txtEmail.getText(),
                            currentUser.getUserCreateDate());
                    user.setId(currentUser.getId());
                    DatabaseConnection connection = new DatabaseConnection();
                    connection.updateExistingUser(user.getId(), user);
                } catch (Exception ex) {
                    utils.displayMessage("Could not update the server.");
                }
            }
        });
        btnDeleteCurrentUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser == null){
                    utils.displayMessage("You must retrieve a user from the database before it can be deleted.");
                }
                else{
                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure you wish to delete this user?", "Test",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION){
                            //delete
                            DatabaseConnection connection = new DatabaseConnection();
                            connection.deleteUserFromDatabase(currentUser.getId());
                            frame.setVisible(false);
                            EditUserScreen userScreen = new EditUserScreen();
                        }
                        else if (result == JOptionPane.NO_OPTION){

                        }
                        else{

                        }
                    }

                }

        });
    }

    /**
     * Clears all fields for easy re-tries
     */
    private void clearFields(){
        txtUserID.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtDateDOB.setText("");
        txtUserType.setText("");
        txtEmail.setText("");
        txtDateCreateDate.setText("");


    }

    /**
     * Fills all available fields with details of a user
     * @param user The user to draw the details from to fill the fields
     */
    private void fillFieldsWithUser(UserClass user){
        txtUserID.setText(user.getStringId());
        txtUsername.setText(user.username);
        txtPassword.setText(user.password);
        txtFirstName.setText(user.firstName);
        txtLastName.setText((user.lastName));
        if (user.getUserDOB() != null){
            txtDateDOB.setText((user.getUserDOB().toString()));
        }
        txtUserType.setText(user.userType);
        txtEmail.setText(user.email);
        if (user.getUserCreateDate() != null) {
            txtDateCreateDate.setText(user.getUserCreateDate().toString());
        }
        currentUser = user;
    }
}
