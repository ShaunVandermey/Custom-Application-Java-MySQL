import org.jdatepicker.DateModel;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**Helper tools used across different parts of the program
 *
 */
public class Utils {

    /**Convert char[] password to String for database purposes
     * This occurs because the Swing component for passwords contains char[] instead of String
     * @param password The char[] to transform into a String
     * @return The complete String
     */
    public String passwordToString(char[] password){
        String fullString = "";
        for (int i = 0; i < password.length; i++){
            fullString += password[i];
        }
        return fullString;
    }

    /**
     * Simply displays a message to the user using the JOptionPane.
     * @param message The message to display.
     */
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    /**Convert the JDatePicker date to a usable SQL one
     * Month starts at 0 due to indexing
     * Deprecated
     * @param year The year to concat
     * @param month The month to concat
     * @param day The day concat
     * @return A String of the concatenated dates
     */
    public String convertDatesToString(int year, int month, int day){
        month += 1;
        String concat = "" + year + "-" + month + "-" + day;
        return concat;
    }

    /**Convert JDatePicker date model to SQL date for use in database
     *
     * @param model The DateModel to convert to SQL date
     * @return An SQL Date that can be used in queries or statements.
     */
    public Date convertToDateSQL(DateModel model){
        Date date = new Date(model.getYear()-1900, model.getMonth(), model.getDay());
        return date;
    }

    /**Checks a string to see if it will be converted to a float, notifies the user if it cannot
     *
     * @param input The String to compare
     * @return A float, defaulting to 0f if the conversion was unsuccessful.
     */
    public float tryConvertStringToFloat(String input){
        float testFloat = 0f;
        try{
            testFloat = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            displayMessage("The field could not be converted to a number. Please make sure Cost and Profit Margin are numbers.");
        }
        return testFloat;
    }

    /**
     * Returns the current Date & Time as a string for logging purposes
     * @return A string containing the current date & time
     */
    public String getDateTimeAsString(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
}
