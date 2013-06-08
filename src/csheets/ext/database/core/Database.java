package csheets.ext.database.core;

/**
 * Contains all information about a given database technology
 * @author João Carreira
 */
public class Database 
{
    private String name, url, adapteeName;
    
    /**
     * constructor
     * @param name name of the database
     * @param url path to the driver
     * @param adapteeName adaptee class that can instantiate the connection object
     */
    public Database(String name, String url, String adapteeName)
    {
        this.name = name;
        this.url = url;
        this.adapteeName = adapteeName;
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
    
    /**
     * getter for adaptee name
     * @return 
     */
    public String getAdapteeName()
    {
        return this.adapteeName;
    }
}
