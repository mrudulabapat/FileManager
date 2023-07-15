/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filemanager_cecs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
//import filePanel;

public class App extends JFrame {
    JPanel panel, topPanel;
    JMenuBar menuBar;
    JMenu fileMenu, treeMenu, windowMenu, helpMenu;
    JMenuItem newItem, openItem;
    JToolBar toolBar;
    JComboBox combobox;
    JButton detailBtn, simpleBtn;
    JLabel statusBar;
    JDesktopPane desktopPane;
    
    public App(){
        
        panel = new JPanel();
        topPanel = new JPanel();
        menuBar = new JMenuBar();
        statusBar = new JLabel("Current Drive");
        desktopPane = new JDesktopPane();
        
    }
    
    public void go(){
        this.setTitle("CECS File Manager");
        panel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        
        createMenuBar();
        topPanel.add(menuBar,BorderLayout.NORTH);
        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(desktopPane, BorderLayout.CENTER);
        
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createToolBar();
        topPanel.add(toolBar, BorderLayout.SOUTH);
        topPanel.setSize(1000,100);
     
        addStatusBar();
        this.add(statusBar, BorderLayout.SOUTH);
        
        this.setVisible(true);
        this.setSize(1000,700);
      
       
    }
    
    public void createMenuBar(){

        fileMenu = new JMenu("File");
        treeMenu = new JMenu("Tree");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");
        
        // File menu items : Add new menuu items for all menus
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        
        menuBar.add(fileMenu);
        menuBar.add(treeMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);
       
        this.setJMenuBar(menuBar);

        
    }

    private void createToolBar() {
        toolBar = new JToolBar();
        JComboBox<String> combobox = new JComboBox<>();
        combobox.addItem("Option 1");
        combobox.addItem("Option 2");
        combobox.addItem("Option 3");
        combobox.addItem("Option 4");
        
        setPreferredSize(new Dimension(200, 30));
        Dimension preferredSize = combobox.getPreferredSize(); 
        preferredSize.height = 10; combobox.setPreferredSize(preferredSize);
        
        toolBar.add(combobox);
        
        detailBtn =  new JButton("Detail");
        toolBar.add(detailBtn);
        
        simpleBtn =  new JButton("Simple");
        toolBar.add(simpleBtn);
     
        
    }

    private void addStatusBar() {
        statusBar.setBorder(BorderFactory.createEtchedBorder()); 
        //Add code to display current drive, free space, used space, total space
    }


}
