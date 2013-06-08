package csheets.ext.database.core;

/**
 * A class that deals with all the data going into and from the databases
 * (UML facade pattern)
 * @author João Carreira
 */
public class DatabaseFacade
{
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
    public void createConnection(String url, String user, String pass)
    {
        DBConnectionAdapterFactory factory = DBConnectionAdapterFactory.getInstance();
        
    }
    
}
