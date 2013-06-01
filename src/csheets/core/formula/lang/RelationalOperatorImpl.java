package csheets.core.formula.lang;

import csheets.core.Cell;
import csheets.core.Value;
import csheets.core.formula.Reference;
import csheets.core.formula.util.ExpressionVisitor;
import java.util.SortedSet;

/**
 *
 * @author João Carreira
 */
public class RelationalOperatorImpl extends RelationalOperator implements Reference
{

    public RelationalOperatorImpl(Reference reference, RangeReference rangeReference, Reference reference0) {
       super();
    }

    @Override
    public <T> boolean compare(Comparable<T> left, T right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdentifier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Value evaluate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SortedSet<Cell> getCells() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Reference reference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
