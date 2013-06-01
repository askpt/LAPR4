package csheets.core.formula.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import java.lang.reflect.Type;

/**
 * The attribution operator := (exclusive for the SignNumber language).
 * @author João Carreira
 */
public class Attribution implements BinaryOperator {

    /** The unique version identifier used for serialization */
    private static final long serialVersionUID = -6922027217525238297L;

    /**
     * Creates a new attribution operator.
     */
    public Attribution()
    {    
    }

    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException 
    {
        return rightOperand.evaluate();
    }

    @Override
    public String getIdentifier() {
        return ":=";
    }

    @Override
    public Value.Type getOperandValueType() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}