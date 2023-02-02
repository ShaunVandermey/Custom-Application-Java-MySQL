import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerializeDeserializeItems {
    private JButton readFileButton;
    private JTextField txtItemName;
    private JTextField txtItemCost;
    private JTextField txtItemProfitMargin;
    private JButton btnSaveFile;
    private JButton btnExit;
    private JLabel btnReadFile;
    private JPanel mainPanel;
    private JFrame frame;
    private JFileChooser chooser;
    private Utils utils = new Utils();

    /**
     * Constructor for this window, containing any required setup.
     */
    public SerializeDeserializeItems(){
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation(500, 400);
        frame.setVisible(true);


        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuScreen mainMenuScreen = new MainMenuScreen();
            }
        });
        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findBinaryFile();
            }
        });
        btnSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeBinaryFile();
            }
        });
    }

    /**
     * Writes the existing item to a binary file
     */
    private void writeBinaryFile(){
        if (!txtItemName.getText().isEmpty() || !txtItemCost.getText().isEmpty() || !txtItemProfitMargin.getText().isEmpty()){
        createItemFromData();
        utils.displayMessage("File written to binary.");
        }
        else{
            utils.displayMessage("Not all fields are filled. Please fill all fields and try to write the file again.");
        }
    }

    /**
     * Creates an SerializeItem object from the existing data
     */
    private void createItemFromData(){
        SerializeItem item = new SerializeItem();
        item.setItemName(txtItemName.getText());
        item.setItemCost(utils.tryConvertStringToFloat(txtItemCost.getText()));
        item.setProfitMargin(utils.tryConvertStringToFloat(txtItemProfitMargin.getText()));
        WriteItemObject writeItemObject = new WriteItemObject();
        writeItemObject.writeItemToPath(item.toString(), item);
    }

    /**
     * Allows user to search for a binary file to load into the current window
     */
    private void findBinaryFile(){
        String fileName;
        chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(mainPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            ReadItemObject readBinaryFile = new ReadItemObject();
            SerializeItem itemData = readBinaryFile.readItemAtPath(fileName);
            fillFieldsFromData(itemData);
        }
    }

    /**
     * Fills this window's fields with the appropriate data from a SerializeItem
     * @param item The SerializeItem to load into the window.
     */
    private void fillFieldsFromData(SerializeItem item){
        txtItemName.setText(item.getItemName());
        txtItemCost.setText(item.getItemCostAsString());
        txtItemProfitMargin.setText(item.getItemMarginAsString());
    }
}
