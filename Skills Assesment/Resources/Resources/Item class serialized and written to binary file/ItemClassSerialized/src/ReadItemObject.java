/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author srao3
 */
import java.io.*;
public class ReadItemObject {
    public static void main(String[] args) {
        SerializeItem itm = null;
        FileInputStream infile = null;
        ObjectInputStream objInfile = null;
        try
        {
            infile = new FileInputStream("itemObj.ser");
            objInfile = new ObjectInputStream(infile);
            itm = (SerializeItem)objInfile.readObject();
            System.out.println("Item read:"+itm.toString());
        }
        catch(IOException e)
        {
            System.out.println("Exception:"+e.getMessage());
        }
        catch (ClassNotFoundException cnf)
        {
            System.out.println("Item class not found");
        }
    }

    /*
    public Item readItemAtPath(String path){
        Item itm = null;
        FileInputStream infile = null;
        ObjectInputStream objInfile = null;
        try
        {
            infile = new FileInputStream(path+".bin");
            objInfile = new ObjectInputStream(infile);
            itm = (Item)objInfile.readObject();
        }
        catch(IOException e)
        {
            System.out.println("Exception:"+e.getMessage());
        }
        catch (ClassNotFoundException cnf)
        {
            System.out.println("Item class not found");
        }
        return itm;
    }
    */


    public SerializeItem readItemAtPath(String path) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        SerializeItem item = new SerializeItem();
        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            item = (SerializeItem) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return item;
    }
    
}
