import javax.swing.*;

//Class used for storing information about different countries for displaying country information

/**
 * A class containing the details of a country for use in the program
 * Includes name, flag, map, and animal for lookup purposes
 */
public class CountryInfo {

    private String myName = "";
    private ImageIcon myFlag = new ImageIcon();
    private ImageIcon myMap = new ImageIcon();
    private ImageIcon myAnimal = new ImageIcon();

    public String getMyName(){
        return myName;
    }

    public ImageIcon getMyFlag() {
        return myFlag;
    }

    public ImageIcon getMyMap() {
        return myMap;
    }

    public ImageIcon getMyAnimal() {
        return myAnimal;
    }

    /**Access internal resources folder to retrieve images based on provided country name
     *and set this instance's references
     *uses nameString to search for appropriate pictures and assigns them
     */
    public CountryInfo(String nameString){
        myName = nameString;
        myFlag = new ImageIcon(getClass().getResource("/images/"+myName+"Flag.png"));
        myMap = new ImageIcon(getClass().getResource("/images/"+myName+"Map.png"));
        myAnimal = new ImageIcon(getClass().getResource("/images/"+myName+"Animal.png"));
    }
}
