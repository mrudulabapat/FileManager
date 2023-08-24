/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filemanager_cecs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.FileAlreadyExistsException;


class PopUpDemo extends JPopupMenu {
    File selectedFile;
  
    
    public PopUpDemo(File selectedFile) {
        this.selectedFile = selectedFile;     
        
        JMenuItem renameItem = new JMenuItem("Rename");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem deleteItem = new JMenuItem("Delete");
        
        renameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRenameCopyDialog("Rename");
            }
        });
        
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRenameCopyDialog("Copying");
            }
        });
               
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteFileDialog();
            }
        });
           
        add(renameItem);
        add(copyItem);
        add(pasteItem);
        addSeparator();
        add(deleteItem);
    }  
    
    public void showRenameCopyDialog(String act) {

        if (selectedFile != null) {
            RenameDialog dialog = new RenameDialog((Frame) SwingUtilities.getWindowAncestor(this), selectedFile, act);
            dialog.setVisible(true);
            
            String newFileName = dialog.getNewName();          
            System.out.println("new file"+newFileName);
                if (!newFileName.trim().isEmpty()) {
                        File newFile = new File(selectedFile.getParent(), newFileName);
                        File selectedDestinationFile = new File(newFileName);
                        if(act.equals("Rename")){  
                            if (selectedFile.renameTo(newFile)) {
                                // File renamed successfully
                                // You may want to update the UI or refresh the file list
                                System.out.println("File Successfully Renamed");
                                JOptionPane.showMessageDialog(this, "File renamed successfully");
                                //updateFileList();
                            } else {
                                // Renaming failed
                                // Handle the error, show an error message, etc.
                                System.out.println("File not Renamed");
                                JOptionPane.showMessageDialog(this, "File couldnot be renamed");
                            }
                        }
                        else if(equals("Copying")){
                                                      
                            if(copyFile(selectedFile.toPath(), selectedDestinationFile.toPath())){
                               System.out.println("File Successfully copied");
                               JOptionPane.showMessageDialog(this, "File copied successfully"); 
                            } 
                            else {                                
                                System.out.println("File couldnot be copied");
                                JOptionPane.showMessageDialog(this, "File couldnot be copied");
                            }
                               
                        }
                        
                        
                    }
                else{
                    // No file selected, show a message
                    JOptionPane.showMessageDialog(
                            this,
                            "Please add a filepath to continue.",
                            "Error!",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }  
        else {
                // No file selected, show a message
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a file for " + act.toLowerCase() +".",
                        "No File Selected",
                        JOptionPane.WARNING_MESSAGE
                );
            }
    }
    
    
    private boolean copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (FileAlreadyExistsException e) {
            // Handle the case when the target file already exists
            // Show a message to inform the user, or take appropriate action
            e.printStackTrace();
     
        } catch (IOException e) {
            // Handle other IO-related exceptions
            e.printStackTrace();
            
        }
        return false;
    }
    
 
    public void showDeleteFileDialog(){
        if (selectedFile != null) {
            DeleteDialog deleteDialog = new DeleteDialog(selectedFile);
        }
        else{
            JOptionPane.showMessageDialog(
                        this,
                        "Please select a file to delete.",
                        "No File Selected",
                        JOptionPane.WARNING_MESSAGE
                );
        }
    } 
    
}

public class PopClickListener extends MouseAdapter {
    private File selectedFile;
    
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()){
            selectedFile =getFileFromMouseEvent(e);
            doPop(e);
        }
            
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()){
            selectedFile = getFileFromMouseEvent(e);
            doPop(e);
        }
    }

    private void doPop(MouseEvent e) {
        PopUpDemo menu = new PopUpDemo(selectedFile);
        menu.show(e.getComponent(), e.getX(), e.getY());

    }
    
    private File getFileFromMouseEvent(MouseEvent e) {
        JList list = (JList) e.getSource();
        int index = list.locationToIndex(e.getPoint());
        if (index >= 0) {
           Object item = list.getModel().getElementAt(index);
           if (item instanceof File) {
              return (File) item;
            }
        }
        return null;
       
        }
    }



