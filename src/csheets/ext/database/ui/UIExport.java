package csheets.ext.database.ui;

import csheets.core.Cell;
import csheets.ext.database.controller.ControllerExport;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
    private JButton btnOK = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    
    /* selected cells to export in a 2D array*/
    private Cell [][]cells;
    
    /* textfields for username, passord and database */
    private JTextField userTxt, pwdTxt, dbText;
   
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
 
        
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        /* TODO auto-generated code */
    }
    
}
