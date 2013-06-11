package csheets.ext.database.ui;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerExport;
import csheets.ext.database.controller.ControllerImport;
import csheets.ext.database.core.Database;
import csheets.ext.database.core.ThreadExport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * GUI to import from the database 
 * @author João Carreira
 */
public class UIImport extends JFrame implements Observer
{
    /* select cells to import in a 2D array */
    private Cell [][]cells;
    
    /* controller object for GUI-controller pattern */
    private ControllerImport ctrlImp;
   
    /* database available drivers sotred in a string and displayed in a combobox */
    private String [][]dbDrivers;
    private String []driversName;
    private JComboBox comboDrivers;
    
    /* buttons */
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnUrl = new JButton("Get url");
    
    /* textfield for username, password and url */
    private JTextField userTxt, urlTxt; 
    private JPasswordField pwd;
    
    /* label to display system information to the user */
    JLabel sysMsg = new JLabel();
    
    /**
     * constructor of the GUI
     * @param cells cells 
     * @throws Exception 
     */
    UIImport(Cell[][] cells) throws Exception
    {
        /* window title */
        super("Import information from a database");
        
        /* saving argument of this function is class variable */
        this.cells = cells;
        
        /* creating a new controller */
        ctrlImp = new ControllerImport(this);
        
        /* getting the list of supported databases and putting it in the combo box */
        dbDrivers = ctrlImp.getDBlist();
        driversName = new String[dbDrivers.length];
        for(int i = 0; i < dbDrivers.length; i++)
        {
            driversName[i] = dbDrivers[i][0];
        }
        comboDrivers = new JComboBox(driversName);
        
        
        
        
        
        /* main panel */
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        /* defining labels */
        JLabel lblDBdrivers = new JLabel("Database");
        JLabel lblUser = new JLabel("Username");
        JLabel lblPwd = new JLabel("Password");
        JLabel lblUrl = new JLabel("URL");
        
        /* setting default system message text and color */
        sysMsg.setText("Connect to a database");
        sysMsg.setForeground(Color.RED);
        
        /* defining text fields */
        userTxt = new JTextField(30);
        pwd = new JPasswordField("");
        urlTxt = new JTextField(30);
        
        /* defining another panel */
        JPanel anotherPanel = new JPanel(new GridLayout(5,1));
        anotherPanel.add(lblDBdrivers);
        anotherPanel.add(comboDrivers);
        anotherPanel.add(lblUser);
        anotherPanel.add(userTxt);
        anotherPanel.add(lblPwd);
        anotherPanel.add(pwd);
        anotherPanel.add(lblUrl);
        anotherPanel.add(urlTxt);
        anotherPanel.add(sysMsg);
        
        /* defining a panel for buttons */
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnOk);
        panelBtn.add(btnCancel);
        panelBtn.add(btnUrl);
        
         /* setting up action listeners */
        HandlesEvent t = new HandlesEvent();
        btnOk.addActionListener(t);
        btnCancel.addActionListener(t);
        btnUrl.addActionListener(t);
        
        /* adding all objects to build the window */
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
            /* default url button */
            if(e.getSource() == btnUrl)
            {
               
            }
            /* button OK*/
            else if(e.getSource() == btnOk)
            {
            }
            /* button cancel */
            else if(e.getSource() == btnCancel)
            {
                dispose();
            }     
        }
    }
    
    
    @Override
    public void update(Observable o, Object o1) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
