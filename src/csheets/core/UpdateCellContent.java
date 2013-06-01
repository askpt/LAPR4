
package csheets.core;

import csheets.core.formula.compiler.FormulaCompilationException;
import java.text.NumberFormat;

/**
 *
 * @author João Carreira
 */
public class UpdateCellContent 
{
    public UpdateCellContent()
    {
    }
    
    public void update(Cell cell, Value value) throws FormulaCompilationException
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
}
