package csheets.ext.database.controller;

import csheets.ext.database.core.DBCsvReader;
import csheets.ext.database.core.Database;
import csheets.ext.database.ui.UIImport;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The controller for UIImport 
 * @author João Carreira
 */
public class ControllerImport 
{
    private ArrayList<Database> dbList;
    
    public ControllerImport(UIImport aThis) 
    {
        
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
    
}