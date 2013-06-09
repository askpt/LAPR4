package csheets.ext.database.core;

import csheets.core.Cell;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates a connection to a HSQL database
 * @author João Carreira
 */
public class HsqlDBConnectionAdaptee implements DBConnectionAdapter
{
    private String driverPath = "org.hsqldb.jdbcDriver";
    private Connection connection;
    private String url, user, pwd;
    
    

    @Override
    public void createConnection(String url, String user, String pass) throws ClassNotFoundException, SQLException
    {
           
        try{
            Class.forName(driverPath);
            
            /* test line -- DELETE AFTERWARDS */
            System.out.println(url);
            
            connection = DriverManager.getConnection(url, user, pass);
            
            /* test line -- DELETE AFTERWARDS */
            System.out.println("HSQLAdaptee: connected!");
        }
        catch(ClassNotFoundException e)
        {
             /* test line -- DELETE AFTERWARDS */
            System.out.println("HSQLAdaptee: class not found");
        }
        catch(SQLException e)
        {
            /* test line -- DELETE AFTERWARDS */
            System.out.println("HSQLAdaptee: sql error");
        }
    }

    @Override
    public void createTable(String tableName, Cell[][] cells, int[][] pk) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTableList() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTableContent()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTable() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
