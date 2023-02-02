import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlagsGui implements ActionListener {
    private JPanel mainPanel;
    private JRadioButton rbtIndia;
    private JRadioButton rbtAustralia;
    private JRadioButton rbtChina;
    private JRadioButton rbtNZ;
    private JLabel lblFlag;
    private JLabel lblMap;
    private JRadioButton rbtAfghanistan;
    private JLabel lblAnimal;
    private JButton btnExit;
    //private ImageIcon india, australia, china, nz;
    private CountryInfo[] countryInfos;
    JFrame frame;

    /**
     * Constructor for this window, containing any required setup.
     */
    public FlagsGui() {

        countryInfos = instanceCountries();

        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocation(500, 25);
        frame.setVisible(true);

        frame.setVisible(true);
        rbtAustralia.addActionListener(this);
        rbtIndia.addActionListener(this);
        rbtChina.addActionListener(this);
        rbtNZ.addActionListener(this);
        rbtAfghanistan.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rbtIndia) {
            setAllCountryInfo("India");
        }
        else if ((e.getSource() == rbtAustralia)){
            setAllCountryInfo("Australia");
        }
        else if ((e.getSource() == rbtNZ)){
            setAllCountryInfo("New Zealand");
        }
        else if ((e.getSource() == rbtChina)){
            setAllCountryInfo("China");
        }
        else if ((e.getSource() == rbtAfghanistan)){
            setAllCountryInfo("Afghanistan");
        }
        else if ((e.getSource() == btnExit)){
            frame.setVisible(false);
            MainMenuScreen mainMenuScreen = new MainMenuScreen();
        }

    }

    /**
     * Sets up the array of countries used in this part of the program
     * @return A CountryInfo[] that includes all necessary info
     */
    private CountryInfo[] instanceCountries(){
        String[] names = {"India", "Australia", "China", "New Zealand", "Afghanistan"};
        CountryInfo[] countries = new CountryInfo[names.length];
        for(int i = 0; i < names.length; i++){
            CountryInfo newInfo = new CountryInfo(names[i]);
            countries[i] = newInfo;
        }
        return countries;
    }

    /**
     * Displays the chosen country's info on the window
     * @param name The name of the country to display
     */
    private void setAllCountryInfo(String name){
        CountryInfo chosenCountry = null;
        for(int i = 0; i < countryInfos.length; i++){
            if (countryInfos[i].getMyName().equals(name)){
                chosenCountry = countryInfos[i];
            }
        }
        lblFlag.setIcon(chosenCountry.getMyFlag());
        lblFlag.setSize(400, 300);
        lblMap.setIcon(chosenCountry.getMyMap());
        lblMap.setSize(400, 300);
        lblAnimal.setIcon(chosenCountry.getMyAnimal());
        lblAnimal.setSize(400, 300);
        lblFlag.setText(chosenCountry.getMyName()+ "");
        lblMap.setText(chosenCountry.getMyName()+ "");
        lblAnimal.setText(chosenCountry.getMyName()+ "");
    }
}
