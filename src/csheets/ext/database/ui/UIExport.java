package csheets.ext.database.ui;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerExport;
import csheets.ext.database.core.Database;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * GUI to export to database 
 * @author João Carreira
 */
public class UIExport extends JFrame implements Observer
{
    /* database available drivers stored in a string and displayed in a combobox */
    private String []dbDrivers;
    private JComboBox comboDrivers;
    
    /* controller object for GUI-controller pattern */
    private ControllerExport ctrlExp;
    
    /* buttons */
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    
    /* selected cells to export in a 2D array*/
    private Cell [][]cells;
    
    /* textfields for username, passord, database and table name */
    private JTextField userTxt, dbTxt, tableTxt;
    private JPasswordField pwd;
    
    /* label to display system information to the user */
    JLabel sysMsg = new JLabel();
   
    /**
     * Export GUI constructor
     * @param cells
     * @throws Exception 
     */
    public UIExport(Cell[][] cells) throws Exception
    {
        /* window title */
        super("Export information to database");
        
        /* saving argument of this function is class variable */
        this.cells = cells;
        
        /* creating a new controller */
        ctrlExp = new ControllerExport(this);
        
        /* getting the list of supported databases and putting it in the combo box */
        dbDrivers = ctrlExp.getDBlist();
        comboDrivers = new JComboBox(dbDrivers);
        
        /* main panel */
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        /* defining labels */
        JLabel lblDBdrivers = new JLabel("Database");
        JLabel lblUser = new JLabel("Username");
        JLabel lblPwd = new JLabel("Password");
        JLabel lblTableName = new JLabel("Table name");
        
        /* setting default system message text and color */
        sysMsg.setText("Fill the required fields");
        sysMsg.setForeground(Color.BLUE);
        
        /* defining text fields */
        dbTxt = new JTextField(20);
        userTxt = new JTextField(20);
        pwd = new JPasswordField("");
        tableTxt = new JTextField(20);
        
        /* defining another panel */
        JPanel anotherPanel = new JPanel(new GridLayout(5,1));
        
        /* adding objects to another panel */
        anotherPanel.add(lblDBdrivers);
        anotherPanel.add(comboDrivers);
        anotherPanel.add(lblUser);
        anotherPanel.add(userTxt);
        anotherPanel.add(lblPwd);
        anotherPanel.add(pwd);
        anotherPanel.add(lblTableName);
        anotherPanel.add(tableTxt);
        anotherPanel.add(sysMsg);
        
        /* defining panel for buttons */
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnOk);
        panelBtn.add(btnCancel);
        
        /* setting up action listeners */
        HandlesEvent t = new HandlesEvent();
        btnOk.addActionListener(t);
        btnCancel.addActionListener(t);
        
        /* adding all object to build window */
        Container c = getContentPane();
        mainPanel.add(anotherPanel);
        mainPanel.add(panelBtn, BorderLayout.SOUTH);
        c.add(mainPanel);
        
        /* other window settings */
        pack();
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
            /* button OK*/
            if(e.getSource() == btnOk)
            {
                /* checks if all fields are filled */
                if(userTxt.getText().trim().length() == 0
                        || pwd.getPassword().length == 0
                        || tableTxt.getText().trim().length() == 0)
                {
                    sysMsg.setText("You can't have any blank field!");
                    sysMsg.setForeground(Color.RED);
                }
            }
            /* button cancel */
            else
            {
                dispose();
            }
                
        }
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        /* TODO auto-generated code */
    }
    
}
