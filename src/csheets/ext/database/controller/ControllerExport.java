package csheets.ext.database.controller;

import csheets.ext.database.core.DatabaseFacade;
import java.util.ArrayList;
import java.util.Observer;

/**
 * The controller for UIExport
 * @author João Carreira
 */
public class ControllerExport 
{
    private ArrayList<Observer> observers;
    private DatabaseFacade facade;
    
    /**
     * Constructor with observer
     * @param o Observer object
     */
    public ControllerExport(Observer o)
    {
        observers = new ArrayList<Observer>();
        addObserver(o);
        /* instantiating new facade */
        facade = new DatabaseFacade();
    }

    /**
     * adds Observer o to arraylist
     * @param o 
     */
    private void addObserver(Observer o) 
    {
        observers.add(o);
    }
}
