
package com.mycompany.filemanager_cecs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import java.text.DecimalFormat;


public class internalFrame extends JInternalFrame {

    private DefaultListModel<File> fileListModel; // Store File objects in the model
    private JList<File> fileList; // Modify to hold File objects
    private String currentDrive;
    private JTree tree; // Add the tree variable
    SimpleDateFormat dateFormat;

    public internalFrame(String currentDrive) {
        
        // Display current drive name
        super("", true, true, true, true);
        this.currentDrive = currentDrive;
        setTitle(currentDrive);
        System.out.println("currentDrive"+currentDrive);
        // Set the internal frame properties (e.g., size, location, etc.)
        setSize(600, 400);

        // Create date format to display file creation date
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Create and add the split pane to the content pane of the internal frame
        JSplitPane splitPane = createSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }
    
    
    public JList<File> getFileList() {
        return fileList;
    }


    private JSplitPane createSplitPane() {
        // Create the components for the split pane
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        //left panel
        leftPanel.setLayout(new BorderLayout());
        getContentPane().add(leftPanel, BorderLayout.CENTER);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(currentDrive);
        JTree tree = new JTree(root);
        JScrollPane leftScrollPane = new JScrollPane(tree);
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        leftScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(leftScrollPane, BorderLayout.CENTER);

        //right panel
        rightPanel.setLayout(new BorderLayout());
        getContentPane().add(rightPanel, BorderLayout.CENTER);
        fileListModel = new DefaultListModel<>(); // Initialize the model
        fileList = new JList<>(fileListModel); // Use the model for the JList

        FileListCellRenderer renderDefault = new FileListCellRenderer();
        fileList.setCellRenderer(renderDefault);
        renderDefault.setShowDetail(false);
        
        JScrollPane rightScrollPane = new JScrollPane(fileList);
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightPanel.add(rightScrollPane, BorderLayout.CENTER);

        leftPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);

        // Create the split pane with the left and right components
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(200);
        splitPane.setContinuousLayout(true);

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
            if (node != null && node.getUserObject() instanceof String) {
                String folderPath = (String) node.getUserObject();
                File folder = new File(folderPath);
                File[] files = folder.listFiles();
                if (folder.exists()) {
                    // Update the internal frame title with the current folder path
                    setTitle(folder.getAbsolutePath());
                }
                
                if (files != null) {
                    fileListModel.clear();
                    for (File file : files) {
                        fileListModel.addElement(file);
                    }
                } else {
                    fileListModel.clear();
                }
            }
        });

        // Add MouseListener to the fileList (right panel)
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = fileList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        File selectedFile = fileListModel.getElementAt(selectedIndex);
                        openFile(selectedFile);
                    }
                }
            }
        });

        fileList.addMouseListener(new PopClickListener());

        // Build the initial tree nodes from the root path
        buildTreeNodes(new File(currentDrive), root);

        return splitPane;
    }

    private void buildTreeNodes(File file, DefaultMutableTreeNode parent) {
        if (file.isDirectory()) {
            // Get all subdirectories and files in the current directory
            File[] files = file.listFiles();
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getAbsolutePath());
            parent.add(node);
            if (files != null) {
                for (File child : files) {
                    // Create a new node for the directory/file and add it to the parent node
                    // If the child is a directory, recursively build its nodes
                    if (child.isDirectory()) {
                        buildTreeNodes(child, node);
                    }
                }
            }
        }
    }

    public void openFile(File file) {
        if (file.isFile()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error opening the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showDetailBtn(boolean showDetail) {

        System.out.println("showDetailBtn clicked " + showDetail);

        fileListModel = new DefaultListModel<>();

        fileList = new JList<>(fileListModel); // Use the model for the JList
        
        FileListCellRenderer customRender = new FileListCellRenderer();
        fileList.setCellRenderer(customRender);
        fileList.repaint();
        fileList.revalidate();
    }

    public class FileListCellRenderer extends DefaultListCellRenderer {

        private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private boolean showDetail;

        public FileListCellRenderer() {
            setShowDetail(false);    
        }
        
        public void setShowDetail(boolean showDetail){
               this.showDetail = showDetail; 
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setShowDetail(showDetail);
            
            if (value instanceof File) {
                File file = (File) value;
                String fileName = file.getName();
                
                if (isSelected) {
                    String fileSize = formatSize(file.length());
                    String fileCreationDate = dateFormatter.format(new Date(file.lastModified()));
                   
                    setText(fileName + "\t\t\t\t" + fileCreationDate + "\t\t\t\t" + fileSize);
                } else {
                    setText(fileName);
                    
                }

                if (file.isFile()) {
                    setIcon(UIManager.getIcon("FileView.fileIcon"));
                } else if (file.isDirectory()) {
                    setIcon(UIManager.getIcon("FileView.directoryIcon"));
                }
            }

            return this;
        }

        private String formatSize(long size) {
            String[] units = {"B", "KB", "MB", "GB", "TB"};
            int unitIndex = 0;
            double sizeInKB = size;

            while (sizeInKB > 1024 && unitIndex < units.length - 1) {
                sizeInKB /= 1024;
                unitIndex++;
            }

            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(sizeInKB) + " " + units[unitIndex];
        }
    }
}
