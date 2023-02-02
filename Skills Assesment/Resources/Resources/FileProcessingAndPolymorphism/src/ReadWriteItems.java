import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadWriteItems {
    private JButton btnFindReadFile;
    private JButton btnSaveFile;
    private JPanel mainPanel;
    private JTextField txtName;
    private JTextField txtCost;
    private JTextField txtProfitMargin;
    private JButton btnExit;
    private JFrame frame;
    private JFileChooser chooser;
    private Utils utils = new Utils();

    /**
     * Constructor for this window, containing any required setup.
     */
    public ReadWriteItems(){
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation(500, 400);
        frame.setVisible(true);


        btnSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = validateFields();
                WriteOutput writeOutput = new WriteOutput();
                writeOutput.WriteFromDataToFile(item);
            }
        });
        btnFindReadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findTextFile();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuScreen mainMenuScreen = new MainMenuScreen();
            }
        });
    }

    private Item validateFields(){
        String name = txtName.getText();
        if (name.isEmpty()){
            JOptionPane.showMessageDialog(null, "The item will not be saved: it has no valid name.");
        }
        float cost = 0;
        boolean costValid = false;
        try{
            cost = Float.parseFloat(txtCost.getText());
            costValid = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The Cost is not a valid number. 0 has been applied.");
        }
        float profit = 0;
        boolean profitValid = false;
        try{
            profit = Float.parseFloat(txtProfitMargin.getText());
            profitValid = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The Profit Margin is not a valid number. ");
        }
        Item item;
        if(costValid && profitValid){
            item = new Item(name, cost, profit);
        }
        else if (costValid && !profitValid){
            item = new Item(name, cost);
        }
        else{
            item = new Item(name);
        }
        return item;
    }

    private void findTextFile(){
        String fileName;
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(mainPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            ReadTextFile readTextFile = new ReadTextFile();
            String[] itemData = readTextFile.readLinesFromFile(fileName);
            createItemFromData(itemData);
        }
    }

    private void createItemFromData(String[] itemData){
        Item itemFromData = new Item();
        String name = itemData[0];
        itemFromData.setItemName(name);
        float price;
        float profitMargin;
        try{
            price = Float.parseFloat(itemData[1]);
            itemFromData.setItemCost(price);
        } catch (NumberFormatException e) {
            utils.displayMessage("The price of the item could not be converted to a number.");
        }
        try{
            profitMargin = Float.parseFloat(itemData[2]);
            itemFromData.setProfitMargin(profitMargin);
        } catch (NumberFormatException e) {
            utils.displayMessage("The profit margin of the item could not be converted to a number.");
        }
        utils.displayMessage("The selected item data is: " + itemFromData.toString());
        loadItemDataToFields(itemFromData);
    }

    private void loadItemDataToFields(Item item){
        txtName.setText(item.getItemName());
        txtCost.setText(item.getItemCostAsString());
        txtProfitMargin.setText(item.getItemMarginAsString());
        utils.displayMessage("The Item has been loaded to the current text fields.");
    }
}
