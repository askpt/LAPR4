package csheets.core.formula.lang;

import csheets.core.*;
import csheets.core.formula.*;

public class Whiledo implements Function {

    public Whiledo() {
    }

    @Override
    public String getIdentifier() {
	return "WHILEDO";
    }

    @Override
    public Value applyTo(Expression[] args) throws IllegalValueTypeException {
	// TODO Auto-generated method stub
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FunctionParameter[] getParameters() {
	// TODO Auto-generated method stub
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isVarArg() {
	return true;
    }

}
