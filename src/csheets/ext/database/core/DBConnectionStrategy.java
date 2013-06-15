package csheets.ext.database.core;

import csheets.core.Cell;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Database connection interface
 * (applying the adapter pattern)
 * @author João Carreira
 */
public interface DBConnectionStrategy 
{
    /**
     * creates a connection to a database driver
     * @param url path to driver
     * @param user username
     * @param pass password
     */
    public void createConnection(String url, String user, String pass) throws ClassNotFoundException, SQLException;
    
    /**
     * creates a new table in the database
     * @param tableName table's name
     * @param cells cells to be added to the table
     */
    public void createTable(String tableName, Cell [][]cells);
    
    /**
     * closes connection from the database
     */
    public void disconnet();
    
    /**
     * saves the result of a SQL expression into an arraylist 
     * @param str SQL expression
     * @return arraylist with statement objects
     */
    public ArrayList queryToArray();
    
    /**
     * returns all the tables availabe int he database
     * @return array with table list
     */
    public String[] getTableList(ArrayList list);
    
    /**
     * returns the data of a given table
     */
    public String[][] getTableContent(String tableName);
    
    /**
     * updates a given table
     */
    public void updateTable();
    
    /**
     * saves a SQL query in an ArrayList
     * @param expression sql expression
     * @return arraylist with indidivual items
     * @throws SQLException 
     */
    public ArrayList queryToArray(String tableName) throws SQLException;
    
    
    /**
     * counts rows and columns of a give database table
     * @param tableName table name
     * @return 2D array with number of rows (index 0) and columns (index 1)
     * @throws SQLException 
     */
    public int[] countsRowsAndCols(String tableName) throws SQLException;
    
    
    /**
     * counts the rows of a given table
     * @param tableName
     * @return
     * @throws SQLException 
     */
    public int countRows(String tableName) throws SQLException;
    
    /**
     * converts database table-content stored in an array list to a 2D String array
     * @param array arralist with table-content
     * @param table 2D array
     * @return table
     */
    public String[][] queryTo2dArray(ArrayList array, String[][] table);

    /**
     * updates a single row in a database table
     * @param tableName table's name
     * @param column table's column
     * @param origin value to be final
     * @param destination target value in the database table 
     */
    public void updateRow(String tableName, String column, String origin, String destination);

    /**
     * inserts a new row into a database table
     * @param tableName database table name
     * @param newData 2D array with data to be added to the database
     */
    public void insertNewData(String tableName, String[][] newData);
}
