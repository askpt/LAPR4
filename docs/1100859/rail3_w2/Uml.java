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
*
*
*
* @author Andre
*/