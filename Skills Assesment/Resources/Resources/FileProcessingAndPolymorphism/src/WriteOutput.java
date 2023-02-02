import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Write report to output file
 */

/**
 *
 * @author srao3
 */
public class WriteOutput {
    /*
    public static void main(String[] args) {
        try {
            Item items[] = new Item[2];
            PrintWriter output;
            Scanner console = new Scanner(System.in);
            
            output = new PrintWriter("item.txt");
            for(int i = 0; i<items.length; i++)
            {
                items[i] = new Item();
                System.out.println("Enter Item name:");
                items[i].setItemName(console.next());
                System.out.println("Enter item price");
                items[i].setItemCost(Float.parseFloat(console.next()));
                items[i].setProfitMargin();
                System.out.println("Profit margin set to: "+items[i].getProfitMargin()*100+"%");
                output.println(items[i].toString());
                System.out.println(items[i].toString());
            }
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println("cannot write file, retry.");
        }
        catch(NumberFormatException ne)
        {
            System.out.println("Please enter a numeric value for cost.");
        }
        
    }

     */

    /**
     * Writes an item to a .txt file
     * @param itemData The ItemData to write into the file
     */
    public void WriteFromDataToFile(Item itemData){
        try {
            FileWriter myWriter = new FileWriter(itemData.getItemName()+ ".txt");
            myWriter.write(itemData.toString());
            myWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully written.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not write to chosen directory. Please make sure the file has a valid name.");
        }
    }
    
}
