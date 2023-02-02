import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class SearchUserScreen {
    private JTable displayTable;
    private JTextField txtFirstName;
    private JButton btnFirstnameSearch;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JButton btnExit;
    private JTextField txtLastName;
    private JButton btnLastnameSearch;
    private JButton btnSearchCreate;
    private JDatePicker dateSearchCreate;
    JFrame frame;
    String[] columns = {"ID", "Username", "First Name", "Last Name", "DOB", "User Type", "Email", "User Create Date"};


    /**
     * Constructor for this window, containing any required setup.
     */
    public SearchUserScreen() {
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLocation(500, 400);
        frame.setVisible(true);

        btnFirstnameSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //grab all users that contain txtFirstName string
                if (!txtFirstName.getText().isEmpty()) {
                    searchByName("_FirstName", txtFirstName.getText());
                }
            }
        });
        btnLastnameSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtLastName.getText().isEmpty()) {
                    searchByName("_LastName", txtLastName.getText());
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

        btnSearchCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByCreateDate(dateSearchCreate);
            }
        });
    }

    /**
     * Searches for all users that match the corresponding contents in the given column
     * And assigns them to the data table
     * @param columnName The column to search through in the Database
     * @param textBoxContent The content to search for
     */
    private void searchByName(String columnName, String textBoxContent) {
        DatabaseConnection connection = new DatabaseConnection();
        ArrayList<UserClass> users = connection.getUsersByString(columnName, textBoxContent);

        assignDataTable(users);
    }

    /**
     * Assigns the table to display all users retrieved from the Database
     * @param users The users to display in the table
     */
    private void assignDataTable(ArrayList<UserClass> users) {
        String[][] userDataToString = new String[users.size()][];
        for (int i = 0; i < users.size(); i++){
            UserClass tempUser = users.get(i);
            userDataToString[i] = tempUser.getDetailsNoPassword();
        }
        for (int i = 0; i < userDataToString.length; i++){
            System.out.println("user");
        }
        DefaultTableModel contactModel = (DefaultTableModel)displayTable.getModel();
        contactModel.setColumnIdentifiers(columns);
        DefaultTableModel model = (DefaultTableModel) displayTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < users.size(); i++){
            String[] data = users.get(i).getDetailsNoPassword();
            model.addRow(data);
        }
        model.fireTableDataChanged();
        displayTable.setDefaultEditor(Object.class, null);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Searches users in the database by creation date, and assigns them to the data table
     * @param date The date to search for.
     */
    private void searchByCreateDate(JDatePicker date){
        DatabaseConnection connection = new DatabaseConnection();
        Utils utils = new Utils();
        Date sqlDate = utils.convertToDateSQL(date.getModel());
        ArrayList<UserClass> users = connection.getUsersByDate("_CreateDate", sqlDate);
        assignDataTable(users);
    }
}
