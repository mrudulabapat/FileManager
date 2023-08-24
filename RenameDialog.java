/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

/**
 *
 * @author bapat
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RenameDialog extends JDialog {
    private JTextField newNameField;

    public RenameDialog(Frame parent, File selectedFile, String act) {
        super(parent, act, true);
        
        String currentDirectory = selectedFile.getParent();
        String currentFileName = selectedFile.getName();  
        
        // Set the content pane with custom padding
        JPanel contentPane = new JPanel(new BorderLayout(5500, 20)); 
        setContentPane(contentPane);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Current Directory:"));
        panel.add(new JLabel(currentDirectory));
        panel.add(new JLabel("Current File Name:"));
        panel.add(new JLabel(currentFileName));
        panel.add(new JLabel("Enter new name:"));
        newNameField = new JTextField();

        panel.add(newNameField);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> {
            newNameField.setText(""); // Clear the text if the user cancels
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);     
    }

    public String getNewName() {
        return newNameField.getText();
    }    
}

