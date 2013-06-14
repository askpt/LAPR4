/*

@startuml doc-files/use_case.png
left to right direction
Actor User
User -> (Insert dowhile loop in the cells)
User -> (Insert eval in the cells)
@enduml

@startuml doc-files/sd_system_do_loops.png
Actor User as us
Participant System as sys

us -> sys : select a cell
us -> sys : insert a sequence of expressions
us -> sys : insert the stop condition of the loop
us -> sys : presses return
sys -> us : display the result of last iteration in the cells
@enduml

@startuml doc-files/sd_system_eval.png
Actor User as us
Participant System as sys

us -> sys : select a cell
us -> sys : insert the eval function
us -> sys : insert the cell expression
us -> sys : presses return
sys -> us : display the result of last iteration in the cells
@enduml


@startuml Diagrams/sd_cell_lang.png
note left of NumberSignExpressionCompiler
 to assign a value to a cell
 we describe method convert() 
end note
note left of NumberSignExpressionCompiler
 this method is recursive
 therefore it is included
 in a loop
end note 
loop recursive calls depending on the depth of the generated ANTLR AST
 NumberSignExpressionCompiler -> ASTPair: getNumberOfChildren()
 alt if numberOfChildren() == 0
  NumberSignExpressionCompiler -> ASTPair: getType()
  alt if node == NUMBER
   NumberSignExpressionCompiler -> Literal: new()
  else if node == STRING
   NumberSignExpressionCompiler -> Literal: new()
  else if node == CELL_REF
   NumberSignExpressionCompiler -> CellReference: new()
  end
   NumberSignExpressionCompiler -> Language: getInstance()
   NumberSignExpressionCompiler -> Language: getFunction()
  opt if function != NULL
   NumberSignExpressionCompiler -> ASTPair: getFirstChild()
   loop recursive calls while getFirstChild() != NULL
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, child)
   end
   
   alt if function instanceof Whiledo
   	loop recursive while condition argument of whiledo == true
   	 NumberSignExpressionCompiler -> NumberSignExpressionCompiler : convert(cell, node)
   	end
   end
   alt if function instanceof Dowhile
   	loop recursive do while condition argument of dowhile == true
   	 NumberSignExpressionCompiler -> NumberSignExpressionCompiler : convert(cell, node)
   	end
   end
   
   NumberSignExpressionCompiler -> FunctionCall: new()
  end
  else if numberOfChildren == 1
   NumberSignExpressionCompiler -> Language: getInstance()
   NumberSignExpressionCompiler -> Language: getUnaryOperator(node.getText())
   loop recursive calls while node.firstChild() != NULL
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, node.firstChild())
   end
    NumberSignExpressionCompiler -> UnaryOperation: new()
  else if numberOfChildren >= 2
   NumberSignExpressionCompiler -> Language: getInstance()
   alt node == ';'
   NumberSignExpressionCompiler -> NumberSignExpressionCompiler : convert(cell, node.getFirstChild())
   note left 
   	convert the current child node
   end note
   NumberSignExpressionCompiler --> NumberSignExpressionCompiler : convert(cell, node.getFirstChild().getNextSibling())
   note left 
   	convert the next child node
   end note
   else
   NumberSignExpressionCompiler -> Language: getBinaryOperator(node.getText())
   alt operator is Attribution
    NumberSignExpressionCompiler -> CellReference: new()
    note left: new code to deal with ':='
    NumberSignExpressionCompiler -> CellReference: getCell()
    NumberSignExpressionCompiler -> ASTPair: getFirstChild()
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert()
    NumberSignExpressionCompiler -> Expression: value = evaluate()
    NumberSignExpressionCompiler -> UpdateCellContent: getInstance()
    NumberSignExpressionCompiler -> UpdateCellContent: triggerUpdate()
    UpdateCellContent -> Cell: setContent(value)
   else else
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, node.getFirstChild())
    NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert(cell, node.getNextSibling()) 
   end
 end
end
@enduml

@startuml doc-files/class_diagram.png
class Whiledo {
	+{static}FunctionParameter[] parameters
	+Whiledo()
	+String getIdentifier()
	+Value applyTo(Expression[] args)
	+FunctionParameter[] getParameters()
	+boolean isVarArg()
}

class Dowhile {
	+{static}FunctionParameter[] parameters
	+Dowhile()
	+String getIdentifier()
	+Value applyTo(Expression[] args)
	+FunctionParameter[] getParameters()
	+boolean isVarArg()
}


interface Function {
}

CellEditor --o Cell
CellEditor: fireEditingStopped()
Cell <|-- CellImpl
Cell: setContent()
CellImpl: Formula formula
CellImpl: getFormula(): Formula
CellImpl: setContent(String content): void
CellImpl: storeContent(): void
CellImpl: fireContentChanged(): void
CellImpl --o Formula
CellImpl -- FormulaCompiler
FormulaCompiler: getInstance()
FormulaCompiler: compile(this, String content)
FormulaCompiler o-- ExpressionCompiler
ExpressionCompiler <|-- ExcelExpressionCompiler
ExpressionCompiler <|-- NumberSignExpressionCompiler
ExpressionCompiler: getStarter()
ExpressionCompiler: compile(Cell cell, String source)
NumberSignExpressionCompiler --o NumberSignFormulaLexer
NumberSignExpressionCompiler -- Language
NumberSignExpressionCompiler -- UpdateCellContent
Cell -- UpdateCellContent
NumberSignExpressionCompiler -- CellReference
NumberSignFormulaLexer --o NumberSignFormulaParser
NumberSignFormulaParser --o ASTPair
NumberSignFormulaParserTokenTypes <|-- NumberSignFormulaParser
FormulaCompiler -- Formula
Formula --|> Expression
Literal --|> Expression
Expression o-- UnaryOperation
NumberSignFormulaParser: expression()
NumberSignFormulaParser: match(NUMBER_SIGN)
NumberSignFormulaParser: comparison()
NumberSignFormulaParser: match(EOF)
NumberSignFormulaParser: getAST()
NumberSignFormulaParser --|> antlr.LLkParser
NumberSignExpressionCompiler: convert() 
Formula: getReferences()
Formula: evaluate()
Expression: evaluate()
ASTPair: getNumberOfChildren()
ASTPair: getType()
ASTPair: getFirstChild()
Language: getInstance()
Language: getFunction()
Language: getUnaryOperator()
Language: getBinaryOperator()
CellReference: getCell()
UpdateCellContent: getInstance()
UpdateCellContent: triggerUpdate()
Function <|.. Whiledo
Function <|.. Dowhile
@enduml


*/

/**
*
* <p>
* <b>Use Case Diagram</b>
* </p>
* <img src="doc-files/use_case.png">
* 
* <p>
* <b>System Sequence Diagram - Loop of Expressions</b>
* </p>
* <img src="doc-files/sd_system_do_loops.png">
* 
* <p>
* <b>System Sequence Diagram - Eval</b>
* </p>
* <img src="doc-files/sd_system_eval.png">
* 
*<p>
*<b>Sequence Diagram - Implementation of loops</b>
*</p>
*<img src="doc-files/sd_cell_lang.png">
*
**<p>
*<b>Class Diagram</b>
*</p>
*<img src="doc-files/class_diagram.png">
*
*
* @author Andre
*/