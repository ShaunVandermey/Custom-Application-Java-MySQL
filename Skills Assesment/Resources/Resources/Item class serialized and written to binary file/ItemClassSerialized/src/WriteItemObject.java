
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author srao3
 */
public class WriteItemObject {
    public static void main(String[] args) {
        /*
        Item itemObject = new Item("Oppo R9S",120.00f);
        itemObject.setProfitMargin();
        FileOutputStream output = null;
        ObjectOutputStream objOutput = null;
        try{
            output = new FileOutputStream("itemObj.bin");
            objOutput = new ObjectOutputStream(output);
            objOutput.writeObject(itemObject);
            output.close();
            objOutput.close();
        }
        catch(IOException e)
        {
            System.out.println("Exception:"+e.getMessage());
        }
        */
    }

    /*
    public void writeItemToPath(String name, Item item){
        FileOutputStream output = null;
        ObjectOutputStream objOutput = null;
        try{
            output = new FileOutputStream(name + ".bin");
            objOutput = new ObjectOutputStream(output);
            objOutput.writeObject(item.toString());
            output.close();
            objOutput.close();
        }
        catch(IOException e)
        {
            System.out.println("Exception:"+e.getMessage());
        }
    }

     */

    public void writeItemToPath(String name, SerializeItem item){
        try{
        FileOutputStream fileOutputStream
                = new FileOutputStream(name);
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(item);
        objectOutputStream.flush();
        objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
