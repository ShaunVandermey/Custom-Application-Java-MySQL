import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextEditor {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile, menuEdit;
    private JEditorPane txtEditorPane;
    private JPanel mainPanel;
    private JFrame frame = new JFrame();
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit, itemCut, itemCopy, itemPaste, itemHelp;
    private File currentFile;
    private boolean previousSave = false;
    private String previousFileName;

    /**
     * Constructor for this window, containing any required setup.
     */
    public TextEditor(){
        frame = new JFrame("Project Portfolio");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocation(500, 400);
        frame.setVisible(true);

        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        itemNew = new JMenuItem("New");
        itemOpen = new JMenuItem("Open");
        itemSave = new JMenuItem("Save");
        itemSaveAs = new JMenuItem("Save As");
        itemExit = new JMenuItem("Exit");
        itemCut = new JMenuItem("Cut");
        itemCopy = new JMenuItem("Copy");
        itemPaste = new JMenuItem("Paste");
        itemHelp = new JMenuItem("Help");
        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemSaveAs);
        menuFile.add(itemExit);
        menuEdit.add(itemCut);
        menuEdit.add(itemCopy);
        menuEdit.add(itemPaste);
        menuEdit.add(itemHelp);
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        frame.setJMenuBar(menuBar);

        itemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Would you like to discard the current text and create a new document?", "Test",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION){
                    frame.setVisible(false);
                    TextEditor textEditor = new TextEditor();
                }
                else{

                }

            }
        });
        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFromFile();
            }
        });
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (previousSave == false){
                    saveFunction();
                }
                else{
                    overwriteSave();
                }
            }
        });
        itemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFunction();
            }
        });
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenuScreen mainMenuScreen = new MainMenuScreen();
            }
        });
        itemCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection selection = new StringSelection(txtEditorPane.getText());
                clipboard.setContents(selection, null);
                txtEditorPane.setText("");
            }
        });
        itemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection selection = new StringSelection(txtEditorPane.getText());
                clipboard.setContents(selection, null);
            }
        });
        itemPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();
                    String toPaste = (String)clipboard.getData(DataFlavor.stringFlavor);
                    txtEditorPane.setText(txtEditorPane.getText() + toPaste);
                } catch (UnsupportedFlavorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        itemHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "You can use the different parts of the toolbar to interact with the Text Box." +
                        "\r\n Open: Selects and loads an already existing .rtf file into the Text Box." +
                        "\r\n Save: Saves the current file. Will open the Save As dialog if the file doesn't already exist." +
                        "\r\n Save As: Allows the user to set the location and name of the file being saved." +
                        "\r\n Exit: Closes this window and returns the user to the Main Menu." +
                        "\r\n Cut: The contents of the Text Box are stored on the clipboard and erases the Text Box contents." +
                        "\r\n Copy: The contents of the Text Box are duplicated onto the clipboard." +
                        "\r\n Paste: Adds the content on the clipboard to the end of the Text Box." +
                        "\r\n Help: Opens this message window in case the user gets stuck.";
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    /**
     * Saves this text file to a location of the user's choice.
     */
    private void saveFunction() {
        if (!txtEditorPane.getText().isEmpty()){
        try {
            String fileName;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify save location");
            fileChooser.setSelectedFile(new File (".rtf"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("rtf file", "rtf"));
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                fileName = fileChooser.getSelectedFile().toString();
                if (!fileName.endsWith("rtf")){
                    fileName += ".rtf";
                }
                previousFileName = fileName;
                previousSave = true;
                FileWriter myWriter = new FileWriter(fileName);
                myWriter.write(txtEditorPane.getText());
                myWriter.close();
                JOptionPane.showMessageDialog(null, "Successfully written.");
            }


        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "Could not write to chosen directory. Please make sure the file has a valid name.");
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "There is nothing in the document to save: Please enter some text first.");
        }
    }

    /**
     * Overwrites the most recently saved file with the data from the current file.
     */
    private void overwriteSave(){
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(previousFileName);
            myWriter.write(txtEditorPane.getText());
            myWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully written.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not write to chosen directory. Please make sure the file has a valid name.");
        }

    }

    /**
     * Reads the file as chosen by the user and loads it into this text editor.
     */
    private void readFromFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify file location");
        fileChooser.setApproveButtonText("Open");
        fileChooser.setFileFilter(new FileNameExtensionFilter("rtf file", "rtf"));
        String line = "";
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().toString();
            try {
                Scanner inFile = new Scanner(new File(fileName));
                while (inFile.hasNextLine()) {
                    line += inFile.nextLine();
                }
                txtEditorPane.setText(line);
                previousSave = true;
                previousFileName = fileName;
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }


    }
}
