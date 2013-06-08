package csheets.ext.database.core;

/**
 * A class that deals with all the data going into and from the databases
 * (UML facade pattern)
 * @author João Carreira
 */
public class DatabaseFacade
{
    private DBConnectionAdapter adapter;
    
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
    public void createConnection(String url, String user, String pass, String adapteeName) throws Exception
    {
        /* gets factory instance */
        DBConnectionAdapterFactory factory = DBConnectionAdapterFactory.getInstance();
        /* gets the corresponding adapter based on the adaptee class name */
        adapter = factory.getDBTechnology(adapteeName);
        
        
    }
    
}
