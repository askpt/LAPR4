/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.database.ui;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerImport;
import csheets.ext.database.controller.ControllerUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import csheets.ui.ctrl.UIController;
import javax.swing.JOptionPane;


/**
 * Table selection GUI (to select a table from the database)
 * @author João Carreira
 */
public class UITableSelectUpdate extends JFrame
{
    /* labels */
    JLabel sysMsg = new JLabel("Select one table from above");
    
    /* buttons */
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    
    /* array with table list */
    private String[] tableArray;
    private ArrayList arrayQueries;
    
    /* array with database table content */
    private String[][] tableData;
    
    /* tablelist */
    private JList tableList;
    
    private ControllerUpdate ctrlUp;
   
    private UIController uiCtrl;
    
    private Cell [][]cells;
    private String [][]selectedCells;
    
    private int numberOfCols;
     
    /**
     * constructor of the GUI for table selection 
     * @param dbName name of the database
     * @throws Exception 
     */
    public UITableSelectUpdate(Cell [][]cells, String dbName, ControllerUpdate ctrlUp)
    {
        /* window title */
        super("Select a table from " + dbName);
        
        /* labels */
        sysMsg.setForeground(Color.BLUE);
        
        this.ctrlUp = ctrlUp;
        
        this.cells = cells;
        
        /* number of columns of current selected cells */
        this.numberOfCols = cells[0].length;
        
        /* gets the table list */
        tableArray = ctrlUp.getTableList();
             
        /* Jlist with table list for database */
        tableList = new JList(tableArray);
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
     * handles events on different GUI objects
     */
    public class HandlesEvent implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            boolean flag = false;
            
            /* ok button */
            if(e.getSource() == btnOk)
            {
                /* loads a given database table to the table data array */
                tableData = ctrlUp.loadTable(tableList.getSelectedValue().toString());
                int numberColsTargetTable = tableData[0].length - 1;
                
                
                
                System.out.println("DATABASE");
                for(int i = 0; i < tableData.length; i++)
                {
                    for(int j = 0; j < tableData[0].length; j++)
                    {
                        
                         System.out.println(tableData[i][j]);
                     }
                    System.out.println("----");
                  }
                
                
                
                
                /* if column number is the same we can update */
                if(numberColsTargetTable == numberOfCols)
                {
                    selectedCells = ctrlUp.cellsTo2dArray(cells);
                    
                System.out.println("SELECTED CELLS");
                for(int i = 0; i < tableData.length; i++)
                {
                    for(int j = 0; j < tableData[0].length; j++)
                    {        
                         System.out.println(tableData[i][j]);
                     }
                    System.out.println("----");
                 }
                    
                    boolean isDifferent = ctrlUp.compareCellsWithDB(tableData, selectedCells);
                    if(isDifferent)
                    {
                        ctrlUp.updateTable(tableList.getSelectedValue().toString(), tableData, selectedCells);
                        JOptionPane.showMessageDialog(null, tableList.getSelectedValue().toString() + " database: data successfully updated!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Database table is already up-to-date!");
                    }
                    
//                    System.out.println("is different? = " + isDifferent);
                    
                    
                    
                    
                    
                    // ConfirmUpdateUI confirmUpUI = new ConfirmUpdateUI()
                }
                /* if it's not we can't update */
                else
                {
                   JOptionPane.showMessageDialog(null, "Error: you can't proceed with update!\nTables differ in column number!\nSelected columns = " + numberOfCols + "\nTable columns = " + numberColsTargetTable);
                }
            }
            /* cancel button */
            else if(e.getSource() == btnCancel)
            {
                dispose();
            }     
        }
    }
}

