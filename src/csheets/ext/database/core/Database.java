package csheets.ext.database.core;

/**
 * Contains all information about a given database technology
 * @author João Carreira
 */
public class Database 
{
    private String name, url; 
    
    /**
     * constructor
     * @param name name of the database
     * @param url path to the driver
     * @param adapteeName adaptee class that can instantiate the connection object
     */
    public Database(String name, String url)
    {
        this.name = name;
        this.url = url;
    }
    
    /**
     * gets database name
     * @return name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * gets database path to driver
     * @return 
     */
    public String getUrl()
    {
        return this.url;
    }
}
