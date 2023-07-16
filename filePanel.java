/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author bapat
 */
public class filePanel extends JPanel{
        private JScrollPane leftScrollPane = new JScrollPane();
        private JTree leftTree = new JTree();
                   
    public filePanel(){
        leftTree.setPreferredSize(null);
        
        leftScrollPane.setViewportView(leftTree);   
        
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);    
        leftScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(leftScrollPane);
        
        
    }
    
}


