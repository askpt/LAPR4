package csheets.ext.database.controller;

import csheets.ext.database.core.DBCsvReader;
import csheets.ext.database.core.Database;
import csheets.ext.database.core.DatabaseFacade;
import csheets.ext.database.ui.UIImport;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;


/**
 * The controller for UIImport 
 * @author João Carreira
 */
public class ControllerImport implements Subject
{
    private ArrayList<Observer> observers;
    private DatabaseFacade facade;
    private ArrayList<Database> dbList;
    
    public ControllerImport(Observer o) 
    {
        observers = new ArrayList<Observer>();
        addObserver(o);
        /* instantiating new facade */
        facade = new DatabaseFacade(); 
    }
    
    /**
     * Connects with a database
     * @param url path to database
     * @param user username
     * @param pass password
     * @param dbName database name
     * @throws Exception 
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

    @Override
    public void addObserver(Observer o) 
    {
       observers.add(o);
    }

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
     * gets the table list of the selected database
     * @param dbName name of the database
     */
    public String[] getTableList() 
    {
        return facade.getTableList();
    }
}
