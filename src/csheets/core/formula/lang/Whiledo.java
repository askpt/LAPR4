package csheets.core.formula.lang;

import csheets.core.*;
import csheets.core.formula.*;

public class Whiledo implements Function {

    public Whiledo() {
    }

    public static final FunctionParameter[] parameters = new FunctionParameter[] { new FunctionParameter(
	    Value.Type.UNDEFINED, "Iterations", false,
	    "The iterators of expression.") };

    @Override
    public String getIdentifier() {
	return "WHILEDO";
    }

    @Override
    public Value applyTo(Expression[] args) throws IllegalValueTypeException {
	// arg[0] = conditionals
	// arg[>0] = iterations

	int i = 1;
	while (args[0].evaluate().toBoolean()) {
	    System.out.println(args[0].evaluate().toBoolean());
	    for (i = 1; (i < ((args.length) - 1)); i++) {
		args[i].evaluate();
		System.out.println("entrou for");
	    }
	    if (args[0].evaluate().toBoolean()) {
		args[i].evaluate();
		System.out.println("entrou if");
	    }
	}

	return args[i].evaluate();
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
