/**
 * Week 1 - Adding new functionalites to formulae language
 * <p>
 * <b>Use Case Diagram</b>
 * </p>
 * <img src="Diagrams/use_case.png">
 *
 * <p>
 * <b>equence Diagram for existing language</b>
 * </p>
 * <img src="Diagrams/sd_current_language.png">
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
@enduml
*/