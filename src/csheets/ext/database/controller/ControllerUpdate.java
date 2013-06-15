package csheets.ext.database.controller;

import csheets.core.Cell;
import csheets.ext.database.core.DBCsvReader;
import csheets.ext.database.core.Database;
import csheets.ext.database.core.DatabaseFacade;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;

/**
 * The controller for UIUpdate
 * @author João Carreira
 */
public class ControllerUpdate implements Subject
{
    private ArrayList<Observer> observers;
    private DatabaseFacade facade;
    private ArrayList<Database> dbList;
    
    public ControllerUpdate() 
    {
       
    }
    
    /**
     * Constructor with observer
     * @param o Observer object
     */
    public ControllerUpdate(Observer o)
    {
        observers = new ArrayList<Observer>();
        addObserver(o);
        /* instantiating new facade */
        facade = new DatabaseFacade();
    }

    /**
     * returns the list of supported databases name
     * @return 2D array with database name and url
     */
    public String[][] getDBlist() throws FileNotFoundException 
    {
        /* new DBCsvReader */
        DBCsvReader reader = new DBCsvReader();
        /* instantiating a new arraylist and getting all databases */
        dbList = new ArrayList<Database>();
        dbList = reader.getDBList();
        
        /* String array to store only the name of the databases */
        String [][]driversName = new String[dbList.size()][2];
        int i = 0;
        for(; i < dbList.size(); i++)
        {
            driversName[i][0] = dbList.get(i).getName();
            driversName[i][1] = dbList.get(i).getUrl();
        }
        /* returns all names of supported databases */
        return driversName;
    }
    
    /**
     * creates connection to a database
     * @param url path to driver
     * @param user username
     * @param pass password
     * @param adapteeName adaptee class name
     */
    public void connect(String url, String user, String pass, String dbName) throws Exception
    {
        try
        {
            facade.createConnection(url, user, pass, dbName);
        }
        /* replace below with proper exceptions */
        catch(SQLException e)
        {
            this.notifyObserver("Error connecting to database!");
        }
        catch(ClassNotFoundException e)
        {
            this.notifyObserver("Error: database driver not found!");
        }
    }
    
//    /**
//     * sets data to be exported
//     * @param cells cells to be exported
//     * @param user username
//     * @param pass password
//     * @param tableName table name
//     */
//    public void setDataToExport(Cell [][]cells, String user, String pass, String tableName)
//    {
//        facade.exportData(cells, tableName);
//    }

    /**
     * adds Observer o to arraylist
     * @param o Observer object
     */
    public void addObserver(Observer o) 
    {
        observers.add(o);
    }
    
    /**
     * removes Observer o from arraylist
     * @param o Observer object
     */
    @Override
    public void removerObserver(Observer o) 
    {
        observers.remove(o);
    }

    @Override
    public void notifyObserver(String str) 
    {
        int i = 0;
        for(; i < observers.size(); i++)
        {
            observers.get(i).update(null, str);
        }
    }
    
    
    /**
     * imports a table from the database
     * @param tableName name of the table
     */
    public String[][] loadTable(String tableName) 
    {
        return facade.loadTable(tableName);
    }

    
    /**
     * gets the table list of the selected database
     * @param dbName name of the database
     */
    public String[] getTableList() 
    {
        return facade.getTableList();
    } 

    /**
     * converts selected spreadsheet content in a 2D String array
     * @param cells selected cells in spreadsheet
     * @return 2D array with content in selected cells
     */
    public String[][] cellsTo2dArray(Cell[][] cells) 
    {
       return facade.cellsTo2dArray(cells);
    }

    /**
     * compares if there's any difference in content between the selected cells
     * in the spreadsheet and the ones imported from the DB
     * @param tableData content imported from BD
     * @param selectedCells cells selected in the spreadsheet
     * @return true if there's any difference, false if they're equal
     */
    public boolean compareCellsWithDB(String[][] tableData, String[][] selectedCells) 
    {
        return facade.compareCellsWithDB(tableData, selectedCells);
    }
}
