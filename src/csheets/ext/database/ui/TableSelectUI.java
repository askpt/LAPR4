/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.database.ui;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * Table selection GUI (to select a select from the database)
 * @author João Carreira
 */
public class TableSelectUI extends JFrame
{
    /**
     * constructor of the GUI for table selection 
     * @param dbName name of the database
     * @throws Exception 
     */
    public TableSelectUI(String dbName)
    {
        /* window title */
        super("Select a table from " + dbName);
        
        
        
        /* other window settings */
        pack();
        // eventually comment below
        setSize(600,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
