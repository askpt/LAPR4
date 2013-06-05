/*

@startuml doc-files/use_case.png
left to right direction
Actor User
User -> (Insert sequence of expressions in the cells)
User -> (Insert loops of expressions with stop conditions in the cells)
@enduml


@startuml doc-files/sd_system_sequences.png
Actor User as us
participant System as sys

us -> sys : select a cell
us -> sys : insert a sequence of expressions
us -> sys : presses return
sys -> sys : compiles expression
sys -> us : display the result in the cells
@enduml


@startuml doc-files/sd_system_loops.png
Actor User as us
Participant System as sys

us -> sys : select a cell
us -> sys : insert the stop condition of the loop
us -> sys : insert a sequence of expressions
us -> sys : presses return
sys -> sys : compiles expression
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

*/

/**
*
* <p>
* <b>Use Case Diagram</b>
* </p>
* <img src="doc-files/use_case.png">
* 
* <p>
* <b>System Sequence Diagram - Sequence of Expressions</b>
* </p>
* <img src="doc-files/sd_system_sequences.png">
* 
* <p>
* <b>System Sequence Diagram - Loop of Expressions</b>
* </p>
* <img src="doc-files/sd_system_loops.png">
*
*<p>
*<b>Sequence Diagram - Implementation of loops</b>
*</p>
*<img src="doc-files/sd_cell_lang.png">
*
*
* @author Andre
*/