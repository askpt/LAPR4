
package csheets.core;

import csheets.core.formula.compiler.FormulaCompilationException;
import java.text.NumberFormat;

/**
 * A singleton to update a cell's content
 * @author João Carreira
 */
public class UpdateCellContent 
{
    
    private static UpdateCellContent instance = null;
    
    /* private constructor as required in the singleton pattern */
    private UpdateCellContent()
    {
    }
    
    /**
     * updates a cell's content
     * @param cell  targeted cell
     * @param value new cell's value
     * @throws FormulaCompilationException 
     */
    public void triggerUpdate(Cell cell, Value value) throws FormulaCompilationException
    {
        if(value.getType() == Value.Type.NUMERIC)
        {
            cell.setContent(value.toString(NumberFormat.getInstance()));
        }
        else
        {
            cell.setContent(value.toString());
        }
    }
    
    /**
     * getInstance
     * @return UpdateCellContent instance
     */
    public static UpdateCellContent getInstance()
    {
        if(instance == null)
        {
            return new UpdateCellContent();
        }
        return instance;
    }
}
