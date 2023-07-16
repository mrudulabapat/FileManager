/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author bapat
 */
public class internalFrame extends JInternalFrame {
    
    public internalFrame(){
        //display current drive name
        super("Current drive", true, true, true, true);
        
        // Set the internal frame properties (e.g., size, location, etc.)
        setSize(200, 200);
        //internalFrame.setLocation(50, 50);

        // Create and add the split pane to the content pane of the internal frame
        JSplitPane splitPane = createSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);

    }
    
    private JSplitPane createSplitPane() {
        // Create the components for the split pane
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setLayout(new BorderLayout());
        // Add the parent panel to the center of the frame
        getContentPane().add(leftPanel, BorderLayout.CENTER);
        
        // Set some content for the panels 
        //left
        filePanel leftscrollpane = new filePanel();
        leftPanel.add(leftscrollpane,BorderLayout.CENTER);
        leftPanel.setBackground(Color.WHITE);
        
        //right
        rightPanel.setBackground(Color.WHITE);

        // Create the split pane with the left and right components
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

        // Set the initial divider location (in pixels)
        splitPane.setDividerLocation(200);

        // Enable continuous layout so the components adjust automatically when resizing
        splitPane.setContinuousLayout(true);

        return splitPane;
    }



}
