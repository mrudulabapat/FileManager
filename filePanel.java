/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/**
 *
 * @author bapat
 */
public class filePanel extends JPanel{
    private JScrollPane leftScrollPane = new JScrollPane();
    private JTree leftTree = new JTree();
    
    public filePanel(){
        leftScrollPane.setViewportView(leftTree);
        
        add(leftScrollPane);
        
    }
    
}


