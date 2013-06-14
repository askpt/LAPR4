package csheets.core.formula.lang;

import csheets.core.*;
import csheets.core.formula.*;

/**
 * A function that implements a loop statement where the first condition was the
 * stop argument and the next arguments are the execution statements
 * 
 * @author Andre
 * 
 */
public class Dowhile implements Function {

	/**
	 * Create a new instance of function do while
	 */
	public Dowhile() {
	}

	/** Parameters: condition, execution statements */
	public static final FunctionParameter[] parameters = new FunctionParameter[] { new FunctionParameter(
			Value.Type.UNDEFINED, "Iterations", false,
			"The iterators of expression.") };

	@Override
	public String getIdentifier() {
		return "DOWHILE";
	}

	@Override
	public Value applyTo(Expression[] args) throws IllegalValueTypeException {
		// arg[last] = conditional
		// arg[>0...last] = iterations
		return args[args.length - 2].evaluate();
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
