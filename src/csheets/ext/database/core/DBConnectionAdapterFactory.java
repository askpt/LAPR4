package csheets.ext.database.core;

/**
 * A factory of database connection adapters
 * @author João Carreira
 */
public class DBConnectionAdapterFactory 
{
    private static DBConnectionAdapterFactory uniqueInstance = null;
    
    /**
     * private constructor (as required in the singleton pattern)
     */
    private DBConnectionAdapterFactory()
    {
        
    }
    
    /**
     * getInstance
     * @return DBConnectionAdapterFactory singleton
     */
    public static DBConnectionAdapterFactory getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new DBConnectionAdapterFactory();
        }
        return uniqueInstance;
    }
}
