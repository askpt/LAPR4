/**
 * Week 1 - Adding new functionalites to formulae language
 * <p>
 * <b>Use Case Diagram</b>
 * </p>
 * <img src="Diagrams/use_case.png">
 *
 * <p>
 * <b>Sequence Diagram for existing language</b>
 * </p>
 * <img src="Diagrams/sd_current_language.png">
 *
 * <p>
 * <b>Sequence Diagram explaining the co-existence of '=' and '#' formulae languages</b>
 * </p>
 * <img src="Diagrams/sd_current_language.png">
 * <p>
 * <b>Sequence Diagram to implement the attribution operator ':='</b>
 * </p>
 * <img src="Diagrams/sd_cell_attribution.png">
 */
/*
@startuml Diagrams/use_case.png
left to right direction
User --> (inserts expression starting with "number signal") 
User --> (assigns expression to a cell)
@enduml 

@startuml Diagrams/sd_current_language.png
note left of CellEditor
 this class is used to 
 edit cells in a spreadsheet
end note
note left of CellEditor
 the method stopCellEditing
 stops editing and updates
 cell content
end note
CellEditor -> CellImpl: setContent(String content)
CellImpl -> FormulaCompiler: getInstance()
CellImpl -> FormulaCompiler: compile(this, String content)
FormulaCompiler -> ExcelExpressionCompiler: getStarter()
opt source.charAt(0) == '='
FormulaCompiler -> ExcelExpressionCompiler: compile(Cell cell, String source)
ExcelExpressionCompiler -> FormulaLexer: new(String source)
ExcelExpressionCompiler -> FormulaParser: new
ExcelExpressionCompiler -> FormulaParser: expression()
FormulaParser -> ASTPair: new
FormulaParser -> FormulaParser: match(EQ)
FormulaParser -> FormulaParser: comparison
FormulaParser -> FormulaParser: match(EOF)
ExcelExpressionCompiler -> FormulaParser: getAST()
ExcelExpressionCompiler -> ExcelExpressionCompiler: convert()
FormulaCompiler -> Formula: new(Cell cell, Expression expression)
end
CellImpl -> Formula: getReferences()
CellImpl -> CellImpl: fireContentChanged()
CellImpl -> Formula: evaluate()
Formula -> Expression: evaluate()
CellEditor -> CellEditor: fireEditingStopped()
@enduml

@startuml Diagrams/sd_numbersign_language.png
note left of CellEditor
 the addition of 
 NumberSignExpressionCompiler
 allows working with both 
 formulae languages
 at the same time
end note
CellEditor -> CellImpl: setContent(String content)
CellImpl -> FormulaCompiler: getInstance()
CellImpl -> FormulaCompiler: compile(this, String content)
FormulaCompiler -> ExcelExpressionCompiler: getStarter()
alt source.charAt(0) == '='
FormulaCompiler -> ExcelExpressionCompiler: compile(Cell cell, String source)
ExcelExpressionCompiler -> FormulaLexer: new(String source)
ExcelExpressionCompiler -> FormulaParser: new
ExcelExpressionCompiler -> FormulaParser: expression()
FormulaParser -> ASTPair: new
FormulaParser -> FormulaParser: match(EQ)
FormulaParser -> FormulaParser: comparison
FormulaParser -> FormulaParser: match(EOF)
ExcelExpressionCompiler -> FormulaParser: getAST()
ExcelExpressionCompiler -> ExcelExpressionCompiler: convert()
FormulaCompiler -> Formula: new(Cell cell, Expression expression)
else
 FormulaCompiler -> NumberSignExpressionCompiler: getStarter()
  opt source.charAt(0) == '#'
   FormulaCompiler -> NumberSignExpressionCompiler: compile(Cell cell, String source)
   NumberSignExpressionCompiler -> NumberSignFormulaLexer: new(String source)
   NumberSignExpressionCompiler -> NumberSignFormulaParser: new
   NumberSignExpressionCompiler -> NumberSignFormulaParser: expression()
   NumberSignFormulaParser -> ASTPair: new
   NumberSignFormulaParser -> NumberSignFormulaParser: match(NSIGN)
   NumberSignFormulaParser -> NumberSignFormulaParser: comparison
   NumberSignFormulaParser -> NumberSignFormulaParser: match(EOF)
   NumberSignExpressionCompiler -> NumberSignFormulaParser: getAST()
   NumberSignExpressionCompiler -> NumberSignExpressionCompiler: convert()
   FormulaCompiler -> Formula: new(Cell cell, Expression expression)
end
end
CellImpl -> Formula: getReferences()
CellImpl -> CellImpl: fireContentChanged()
CellImpl -> Formula: evaluate()
Formula -> Expression: evaluate()
CellEditor -> CellEditor: fireEditingStopped()
@enduml

@startuml Diagrams/sd_cell_attribution.png
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
  else if numberOfChildren == 2
   NumberSignExpressionCompiler -> Language: getInstance()
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