/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

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
        JScrollPane leftscrollpane = createScrollPaneWithTree();
      
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

    private JScrollPane createScrollPaneWithTree() {
// Create the root node for the JTree
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("C:\\Users\\bapat\\OneDrive\\Desktop\\CSULB_Course\\Software Testing");

    // Get the list of files and directories from the C drive
    File[] roots = File.listRoots();

    // Create nodes for each root (e.g., C:\, D:\, etc.)
    for (File file : roots) {
        DefaultMutableTreeNode driveNode = new DefaultMutableTreeNode(file.getAbsolutePath());
        root.add(driveNode);

        // Traverse the file system and add nodes for folders and files
        buildTreeNodes(file, driveNode);
    }

    // Create the JTree with the root node
    JTree tree = new JTree(root);

    // Create the scroll pane with the JTree as its view
    JScrollPane scrollPane = new JScrollPane(tree);

    return scrollPane;
}

private void buildTreeNodes(File file, DefaultMutableTreeNode parent) {
    if (file.isDirectory()) {
        // Get all subdirectories and files in the current directory
        File[] files = file.listFiles();

        if (files != null) {
            for (File child : files) {
                // Create a new node for the directory/file and add it to the parent node
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(child.getName());
                parent.add(node);

                // If the child is a directory, recursively build its nodes
                if (child.isDirectory()) {
                    buildTreeNodes(child, node);
                }
            }
        }
    }
}



}
