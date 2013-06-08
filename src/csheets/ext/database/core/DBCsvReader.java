package csheets.ext.database.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads a cvs file containing relevant information about the database
 * technology
 * @author João Carreira
 */
public class DBCsvReader 
{
    private ArrayList<Database> dbList;
    /* path to settings file */
    private final String filePath = "src-resources/csheets/ext/database/settings.csv";
    /* csv file */
    File f = new File(filePath);
    
    
    /**
     * constructor
     */
    public DBCsvReader()
    {
    }
    
    /**
     * returns a list of supported databases
     * @return ArrayList of type Database with supported databases
     */
    public ArrayList<Database> getDBList() throws FileNotFoundException
    {
        updateDBList();
        return dbList;
    }
    
    /**
     * updates the list of the current supported databases into the arraylist
     * @throws FileNotFoundException 
     */
    public void updateDBList() throws FileNotFoundException
    {
        Scanner fin = new Scanner(f);
        dbList = new ArrayList<Database>();
        while(fin.hasNextLine())
        {
            String temp = fin.nextLine();
            String []temp2 = temp.split(";");
            dbList.add(new Database(temp2[0], temp2[1]));
        }
    }
    
}
