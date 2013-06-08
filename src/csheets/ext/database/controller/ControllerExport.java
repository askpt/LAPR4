package csheets.ext.database.controller;

import csheets.ext.database.core.DBCsvReader;
import csheets.ext.database.core.Database;
import csheets.ext.database.core.DatabaseFacade;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observer;

/**
 * The controller for UIExport
 * @author João Carreira
 */
public class ControllerExport 
{
    private ArrayList<Observer> observers;
    private DatabaseFacade facade;
    private ArrayList<Database> dbList;
    
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
     * adds Observer o to arraylist
     * @param o 
     */
    private void addObserver(Observer o) 
    {
        observers.add(o);
    }

    /**
     * returns the list of supported databases name
     * @return 
     */
    public String[] getDBlist() throws FileNotFoundException 
    {
        /* new DBCsvReader */
        DBCsvReader reader = new DBCsvReader();
        /* instantiating a new arraylist and getting all databases */
        dbList = new ArrayList<Database>();
        dbList = reader.getDBList();
        
        /* String array to store only the name of the databases */
        String []driversName = new String[dbList.size()];
        int i = 0;
        for(; i < dbList.size(); i++)
        {
            driversName[i] = dbList.get(i).getName();
        }
        /* returns all names of supported databases */
        return driversName;
    }
}
