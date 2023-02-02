import java.sql.Date;

/**Important UserClass that acts as the basis for most of the program
 *
 */
public class UserClass {

    private int id;
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public Date userDOB;
    public String userType;
    public String email;
    public Date userCreateDate;

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }


    public Date getUserDOB() {
        return userDOB;
    }

    public String getUserType(){
        return userType;
    }
    public String getEmail(){
        return email;
    }


    public int getId() {
        return id;
    }

    public String getStringId(){
        return ""+id+"";
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getUserCreateDate(){
        return userCreateDate;
    }

    public UserClass(){
    }

    public UserClass(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserClass(String username, String password, String firstName, String lastName, Date dateOfBirth, String userType, String email, Date createDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userDOB = dateOfBirth;
        this.userType = userType;
        this.email = email;
        this.userCreateDate = createDate;
    }

    /**
     * Adds this user into the database
     */
    public void addUserToDatabase(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        int newID = dbConnection.executeNewIDQuery();
        String statement = "INSERT INTO UserTable VALUES (" + newID +",'" + username + "', '"+password+"', '"+firstName+"', '"+lastName+"', '"+userDOB+"', '"+userType+"', '"+email+"', '"+userCreateDate+"');";
        dbConnection.executeStatement(statement);
    }

    /**
     * Checks the database to see if this user's username and password exist.
     * Useful for password validation
     * @return An array of length 2 that holds this user's username and password.
     */
    public String[] checkLoginInfo(){
        DatabaseConnection dbConnection = new DatabaseConnection();
        String query = "SELECT * FROM UserTable WHERE _Username='"+username+"' AND _Password='"+password+"';";
        return dbConnection.executeUsernameQuery(query);
    }

    /**
     * Retrieves this user's details, omitting the password
     * @return A Sting[] of the details of this user, with parameters in order of ID, Username, FirstName, LastName, DOB, user type, Email, and Creation date.
     */
    public String[] getDetailsNoPassword(){
        String[] details = {getStringId(), getUsername(), getFirstName(), getLastName(), getUserDOB().toString(), getUserType(), getEmail(), getUserCreateDate().toString()};
        return details;
    }

}
