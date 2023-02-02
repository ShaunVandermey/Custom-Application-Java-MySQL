import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Process sample.txt using Scannner
 */

/**
 * @author srao3
 */


public class ReadTextFile {
    /*
    public static void main(String[] args) {        
        String fileName = "sample.txt";
        String currentRecord = "";
        String [] fields;
        //print main heading
        System.out.printf("%30s\n","Phone report");
        MyUtils.printLine('x',120);
        try {
            Scanner inFile =  new Scanner(new File(fileName));
            currentRecord = inFile.nextLine();
            fields = currentRecord.split(";");
            for(String item:fields)
                System.out.printf("%-20s",item);
            System.out.println("");
            MyUtils.printLine(120);
            while(inFile.hasNext())
            {
                currentRecord = inFile.nextLine();
                fields = currentRecord.split(";");
                for(String item:fields)
                    System.out.printf("%-20s",item);  
                System.out.println("");
            }            
        } catch (FileNotFoundException fe) {
            System.out.println("Cannot find file:"+fileName);
        }
    }

     */

    /**
     * Reads and returns all the lines in a given file
     * @param fileName The name of the file to read
     * @return An array of all lines within the file
     */
    public String[] readLinesFromFile(String fileName){
        String[] lines = {"", "", ""};
        try{
            Scanner inFile = new Scanner(new File(fileName));
            String line = "";
            while(inFile.hasNextLine()){
                line += inFile.nextLine();
            }
            //got the lines, now to split
            lines = line.split(";");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return lines;
    }
}
