package csheets.ext.database.ui;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * GUI to export to database 
 * @author João Carreira
 */
public class UIUpdate extends JFrame implements Observer
{
    /* database available drivers stored in a string and displayed in a combobox */
    private String [][]dbDrivers;
    private String []driversName;
    private JComboBox comboDrivers;
    
    /* controller object for GUI-controller pattern */
    private ControllerUpdate ctrlUp;
    
    /* export thread */
//    ThreadUpdate thrUp;
    
    /* buttons */
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private JButton btnUrl = new JButton("Get URL");
    
    /* selected cells to update in a 2D array*/
    private Cell [][]cells;
    
    /* textfields for username, passord, database and table name */
    private JTextField userTxt, dbTxt, urlTxt;
    private JPasswordField pwd;
    
    /* label to display system information to the user */
    JLabel sysMsg = new JLabel();
    
    /**
     * Update GUI constructor
     * @param cells
     * @throws Exception 
     */
    public UIUpdate(Cell[][] cells) throws Exception
    {
        /* window title */
        super("Update information with database");
        
        /* saving argument of this function is class variable */
        this.cells = cells;
        
        /* creating a new controller */
        ctrlUp = new ControllerUpdate(this);
        
        /* getting the list of supported databases and putting it in the combo box */
        dbDrivers = ctrlUp.getDBlist();
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
        sysMsg.setText("Fill the required fields");
        sysMsg.setForeground(Color.BLUE);
        
        /* defining text fields */
        dbTxt = new JTextField(30);
        userTxt = new JTextField(30);
        pwd = new JPasswordField("");
        urlTxt = new JTextField(30);
        
        /* defining another panel */
        JPanel anotherPanel = new JPanel(new GridLayout(7,1));
        anotherPanel.add(lblDBdrivers);
        anotherPanel.add(comboDrivers);
        anotherPanel.add(lblUser);
        anotherPanel.add(userTxt);
        anotherPanel.add(lblPwd);
        anotherPanel.add(pwd);
        anotherPanel.add(lblUrl);
        anotherPanel.add(urlTxt);
        anotherPanel.add(sysMsg);
        
        /* defining panel for buttons */
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnOk);
        panelBtn.add(btnCancel);
        panelBtn.add(btnUrl);
        
        /* setting up action listeners */
        HandlesEvent t = new HandlesEvent();
        btnOk.addActionListener(t);
        btnCancel.addActionListener(t);
        btnUrl.addActionListener(t);
        
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
            /* default url button */
            if(e.getSource() == btnUrl)
            {
                urlTxt.setText(dbDrivers[comboDrivers.getSelectedIndex()][1]);
            }
            
            /* button OK*/
            else if(e.getSource() == btnOk)
            {
                /* checks if there's at least two rows to proceed with export */
                 if(cells.length < 2)
                {
                    JOptionPane.showMessageDialog(null, "Error: you must select at least\ntwo rows to update!");
                    dispose();
                }
                 
                /* checks if all fields are filled */
                if(userTxt.getText().trim().length() == 0
                        || pwd.getPassword().length == 0)
                {
                    sysMsg.setText("Username/password");
                    sysMsg.setForeground(Color.RED);
                }
                /* if all fields are filled tries to connect */
                else
                {
                   
                }
            }
            /* button cancel */
            else if(e.getSource() == btnCancel)
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
