/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.database.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        
        /* labels */
        JLabel sysMsg = new JLabel("Select one table from above");
        
        /* teste string */
        String []test = {"test", "teste2", "testes"};
        /* Jlist */
        JList tableList = new JList(test);
        tableList.setVisibleRowCount(5);
        tableList.setPreferredSize(new Dimension(100,100));
        JScrollPane scroll = new JScrollPane(tableList);
        
        /* buttons */
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        
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
        buttonPanel.add(btnOK);
        buttonPanel.add(btnCancel);
        
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
}
