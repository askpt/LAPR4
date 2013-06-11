package csheets.ext.database.core;

import csheets.core.Cell;
import java.sql.SQLException;

/**
 * A class that deals with all the data going into and from the databases
 * (UML facade pattern)
 * @author João Carreira
 */
public class DatabaseFacade
{
    private DBConnectionStrategy adapter;
    private String tableName;
    private Cell [][]cells;
    private int []pk;
    
    /**
     * constructor
     */
    public DatabaseFacade()
    {
    }
    
    /**
     * creates a connection to a database driver
     * @param url path to the driver
     * @param user username
     * @param pass password
     */
    public void createConnection(String url, String user, String pass, String dbName) throws SQLException, ClassNotFoundException, Exception
    {
        /* gets factory instance */
        DBConnectionFactory factory = DBConnectionFactory.getInstance();
        /* gets the corresponding adapter based on the adaptee class name */
        adapter = factory.getDBTechnology(dbName);
        adapter.createConnection(url, user, pass);
    }
    
   /**
    * exports data to a database
    * @param cells cells to be exported
    * @param tableName table's name in the database
    */
    public void exportData(Cell [][]cells, String tableName)
    {
        this.tableName = tableName;
        this.cells = cells;
        adapter.createTable(tableName, cells);
        adapter.disconnet();
    }
    
}
