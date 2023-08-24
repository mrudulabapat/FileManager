/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author bapat
 */
public class DeleteDialog extends JDialog {
    public DeleteDialog(File selectedFile){
    int confirmResult = JOptionPane.showConfirmDialog(
                this,
                "Delete: " + selectedFile,
                "Deleting!!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        
        if (confirmResult == JOptionPane.YES_OPTION) {
            // User confirmed the deletion, perform the delete operation
            deleteFile(selectedFile.toPath());

            // Refresh the file list display
            //updateFileList();
        } else {
            // User canceled the deletion, do nothing or show a message if needed
        }
    }
    
    private void deleteFile(Path filePath) {
        try {
            Files.delete(filePath);
            
        } catch (IOException e) {
            // Handle the exception if the file deletion fails
            e.printStackTrace();
            
        }
    }
    
}
