

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

public class DatabaseConnection {

    String serverAddress = "jdbc:mysql://localhost:3306/test";
    Utils utils = new Utils();
    Logger newLog = Logger.getLogger(DatabaseConnection.class.getName());
    /**Internal testing function used to verify connection to the database.
     *Not actually used in the program but handy during development
     * @exception SQLException on statement/connection error
     * @exception ClassNotFoundException
     */
    public void testConnection(){
        String selectStmt = "select * from UserTable;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectStmt);
            if (rs != null){
                System.out.println("Connection Success");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /**Very generic function that allows statements to be executed upon the database.
     *Used internally by different program features
     * @param statement The String statement to be executed
     * @exception SQLException
     * @exception ClassNotFoundException
     */

    public void executeStatement(String statement){
        String temp = utils.getDateTimeAsString();

        try {
            newLog.info("Opening connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            stmt.execute(statement);
            newLog.info("Closing connection.");
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", e);
        }
        JOptionPane.showMessageDialog(null, "Database updated.");
    }

    /**Returns the largest Primary Key from the database and increments it by 1 for a new database entry
     *
     * @return The next ID to be entered into the Database
     * @exception SQLException
     * @exception ClassNotFoundException
     */
    public int executeNewIDQuery(){
        int id = 0;
        String query = "SELECT MAX(_ID) AS Max_Id from UserTable;";
        try {
            newLog.info("Opening connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                id = rs.getInt(1);
            }
            newLog.info("Closing connection.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", ex);
        }
        return id + 1;
    }

    /**Retrieves a user's username and password that match the query parameters
     * Used in UserClass to verify that the username and password are correct
     * @param query The query that retrieves the username and password
     * @return String[] containing only the username and password
     * @exception SQLException
     * @exception ClassNotFoundException
     */

    public String[] executeUsernameQuery(String query){
        String[] toReturn = {"", ""};
        try {
            newLog.info("Opening connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                toReturn[0] = rs.getString(2);
                toReturn[1] = rs.getString(3);
            }
            newLog.info("Closing connection.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", ex);
        }
        return toReturn;
    }

    /**Retrieves a user's information based upon the user ID input
     *
     * @param id The ID of the user to retrieve
     * @return A UserClass based on the id parameter
     * @exception SQLException
     * @exception ClassNotFoundException
     */
    public UserClass getUser(int id){
        String query = "SELECT * FROM UserTable WHERE _ID="+id+";";
        UserClass retrievedUser = new UserClass();
        try {
            newLog.info("Opening connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            retrievedUser = new UserClass(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDate(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getDate(9));
            retrievedUser.setId(rs.getInt(1));

            }
            newLog.info("Closing connection.");
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", e);
        }
        return retrievedUser;
    }

    /**Retrieves a user's information based on a matching email address
     *Only returns first result found. Smarter way would be to not allow user to create
     *duplicate email addresses
     * @param emailAddress The email address of the user to find
     * @return A user found from the input email address
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public UserClass getUser(String emailAddress){
        String query = "SELECT * FROM UserTable _Email WHERE _Email LIKE '"+emailAddress+"';";
        UserClass retrievedUser = new UserClass();
        try {
            newLog.info("Opening connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            retrievedUser = new UserClass(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDate(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getDate(9));
            retrievedUser.setId(rs.getInt(1));
            }
            newLog.info("Closing connection.");
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", e);
        }
        return retrievedUser;
    }

    /**Allows the program to update a user's details based on UserClass and ID primary key
     *
     * @param id The primary key ID of the user
     * @param user The values to update the user to
     */
    public void updateExistingUser(int id, UserClass user){
        String statement = "UPDATE UserTable SET _Username='"+user.getUsername()+"'," +
                "_Password='"+user.getPassword()+"',"+
                "_FirstName='"+user.getFirstName()+"',"+
                "_LastName='"+user.getLastName()+"',"+
                "_DateOfBirth='"+user.getUserDOB()+"',"+
                "_UserType='"+user.getUserType()+"',"+
                "_Email='"+user.getEmail()+"',"+
                "_CreateDate='"+user.getUserCreateDate()+"' WHERE _ID="+id+";";
        executeStatement(statement);
    }

    /**Removes a user from the database based on Primary Key ID
     *
     * @param id The ID of the user to remove
     */
    public void deleteUserFromDatabase(int id){
        String statement = "DELETE FROM UserTable WHERE _ID="+id+";";
        executeStatement(statement);
        JOptionPane.showMessageDialog(null, "User Deleted");
    }

    /**Returns an ArrayList of UserClass based on search parameters
     *
     * @param columnName The column name to search through
     * @param toSearchFor The value in columnName to search for
     * @return An ArrayList of the valid users that meet the parameters
     */
    public ArrayList<UserClass> getUsersByString(String columnName, String toSearchFor){
        String query = "SELECT * FROM UserTable WHERE "+columnName+" LIKE '%"+toSearchFor+"%';";
        ArrayList<UserClass> users = new ArrayList<UserClass>();
        try {
            newLog.info("Opening connection.");
            UserClass retrievedUser = new UserClass();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                retrievedUser = new UserClass(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9));
                retrievedUser.setId(rs.getInt(1));
                users.add(retrievedUser);
            }
            newLog.info("Closing connection.");
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", e);
        }
        return users;
    }

    /**Returns an ArrayList of UserClass based on the matching date, either the BirthDate or the CreateDate
     *
     * @param columnName The column name to search through
     * @param toSearchFor The date to search in the columnName
     * @return An ArrayList of the valid users that meet the parameters
     */
    public ArrayList<UserClass> getUsersByDate(String columnName, Date toSearchFor){
        String query = "SELECT * FROM UserTable WHERE "+columnName+" LIKE '%"+toSearchFor+"%';";
        ArrayList<UserClass> users = new ArrayList<UserClass>();
        try {
            newLog.info("Opening connection.");
            UserClass retrievedUser = new UserClass();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(serverAddress, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                retrievedUser = new UserClass(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9));
                retrievedUser.setId(rs.getInt(1));
                users.add(retrievedUser);
            }
            newLog.info("Closing connection.");
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "Class not found exception.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            newLog.log(Level.SEVERE, "SQL Exception occurred.", e);
        }
        return users;
    }
}
