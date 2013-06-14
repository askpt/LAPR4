/**
 * 
 */
package csheets.core.formula.lang;

import csheets.core.*;
import csheets.core.formula.*;

/**
 * Class for eval expressions
 * 
 * @author Andre
 * 
 */
public class Eval implements Function {

	/** the function parameters */
	public static final FunctionParameter[] parameters = new FunctionParameter[] { new FunctionParameter(
			Value.Type.TEXT, "Expression", false,
			"An expression to evaluated in eval") };

	@Override
	public String getIdentifier() {
		return "EVAL";
	}

	@Override
	public Value applyTo(Expression[] args) throws IllegalValueTypeException {
		return args[0].evaluate();
	}

	@Override
	public FunctionParameter[] getParameters() {
		return parameters;
	}

	@Override
	public boolean isVarArg() {
		return true;
	}
}
