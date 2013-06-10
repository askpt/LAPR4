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
 * The controller for UIExport
 * @author João Carreira
 */
public class ControllerExport implements Subject
{
    private ArrayList<Observer> observers;
    private DatabaseFacade facade;
    private ArrayList<Database> dbList;
    
    public ControllerExport() 
    {
       
    }
    
    /**
     * Constructor with observer
     * @param o Observer object
     */
    public ControllerExport(Observer o)
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
    
    /**
     * sets data to be exported
     * @param cells cells to be exported
     * @param user username
     * @param pass password
     * @param tableName table name
     */
    public void setDataToExport(Cell [][]cells, String user, String pass, String tableName)
    {
        facade.exportData(cells, tableName);
    }

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
}
