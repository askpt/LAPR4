package csheets.ext.persistance.exportdb.controllers;

import java.util.Observer;

/**
 * Subject class of observer pattern
 * @author João Carreira
 */
@Deprecated
public interface Subject
{
    public void addObserver(Observer obs);
    public void removeObserver(Observer obs);   
    public void alertObsevers(String message);
}
