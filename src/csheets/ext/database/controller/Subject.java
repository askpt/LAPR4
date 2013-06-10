package csheets.ext.database.controller;

import java.util.Observer;

/**
 * The subject class of observer
 * @author João Carreira
 */
public interface Subject 
{
    public void addObserver(Observer o);
    public void removerObserver(Observer o);
    public void notifyObserver(String str);
}
