package csheets.ext.database.core;

import csheets.core.Cell;

/**
 * Database connection interface
 * (applying the adapter pattern)
 * @author João Carreira
 */
public interface DBConnectionAdapter 
{
    /**
     * creates a connection to a database driver
     * @param url path to driver
     * @param user username
     * @param pass password
     */
    public void createConnection(String url, String user, String pass);
    
    /**
     * creates a new table in the database
     * @param tableName table's name
     * @param cells cells to be added to the table
     * @param pk primary key
     */
    public void createTable(String tableName, Cell [][]cells, int [][]pk);
    
    /**
     * returns all the tables availabe int he database
     */
    public void getTableList();
    
    /**
     * returns the data of a given table
     */
    public void getTableContent();
    
    /**
     * updates a given table
     */
    public void updateTable();
}
