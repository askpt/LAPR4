/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.database.ui;

import csheets.ext.database.controller.ControllerImport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import csheets.ext.database.ui.UIImport;

/**
 * Table selection GUI (to select a select from the database)
 * @author João Carreira
 */
public class UITableSelect extends JFrame
{
    
    /* labels */
    JLabel sysMsg = new JLabel("Select one table from above");
    
    /* buttons */
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnPreview = new JButton("Preview");
    
    /* array with table list */
    private String[] tableArray;
    private ArrayList arrayQueries;
    
    private ControllerImport ctrlImp;
    
    /**
     * constructor of the GUI for table selection 
     * @param dbName name of the database
     * @throws Exception 
     */
    public UITableSelect(String dbName, ControllerImport ctrlImp)
    {
        /* window title */
        super("Select a table from " + dbName);
        
        /* labels */
        sysMsg.setForeground(Color.BLUE);
        
        this.ctrlImp = ctrlImp;
        
        /* gets the table list */
        tableArray = ctrlImp.getTableList();
        
        /* Jlist with table list for database */
        JList tableList = new JList(tableArray);
        tableList.setVisibleRowCount(5);
        tableList.setPreferredSize(new Dimension(100,100));
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableList.setSelectedIndex(0);
        JScrollPane scroll = new JScrollPane(tableList); 
        
        /* main panel */
        JPanel mainPanel = new JPanel(new GridLayout(3,3));
        
        /* list panel */
        JPanel anotherPanel = new JPanel();
        anotherPanel.add(scroll);
        anotherPanel.add(sysMsg);
        
        /* sysMsg panel */
        JPanel msgPanel = new JPanel();
        msgPanel.add(sysMsg);
        
        /* button panel */
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnOk);
//        buttonPanel.add(btnPreview);
        buttonPanel.add(btnCancel);
        
        /* setting up action listeners */
        HandlesEvent t = new HandlesEvent();
        btnOk.addActionListener(t);
        btnCancel.addActionListener(t);
//        btnPreview.addActionListener(t);
        
        /* putting everything together */
        Container c = getContentPane();
        mainPanel.add(anotherPanel);
        mainPanel.add(msgPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        c.add(mainPanel);     
        
        /* other window settings */
        setSize(300,350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    
     /**
     * handles event on different GUI objects
     */
    public class HandlesEvent implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            /* ok button */
            if(e.getSource() == btnOk)
            {
                
            }
            
//            /* preview button */
//            else if(e.getSource() == btnPreview)
//            {
//               
//            }
            
            /* cancel button */
            else if(e.getSource() == btnCancel)
            {
                dispose();
            }     
        }
    }

    
}
