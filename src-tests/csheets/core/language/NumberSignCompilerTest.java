package csheets.core.language;

import static org.junit.Assert.*;

import org.junit.*;

import csheets.core.*;
import csheets.core.formula.compiler.FormulaCompilationException;

/**
 * Class to test the Number Sign Compiler
 * 
 * @author Andre
 * 
 */
public class NumberSignCompilerTest {

    private static Workbook workbook;
    private static Spreadsheet sheetAttri;
    private static Spreadsheet sheetSeq;

    /**
     * Create a new workbook for the program
     * 
     * @throws Exception
     *             any Exception can occur
     */
    @BeforeClass
    public static void setUpClass() throws Exception {

	/** create a new instance of workbook */
	workbook = new Workbook(3);

	/** Change to the first spreadsheet */
	sheetAttri = workbook.getSpreadsheet(0);

	/** Change to the second spreadsheet */
	sheetSeq = workbook.getSpreadsheet(1);

    }

    /**
     * Tests the attribution function
     */
    @Test
    public void testAttribution() {

	try {
	    // test 1 -> test in cell a1, to modify cell a2 to be 3
	    String expression1 = "#a2:=3";
	    Cell cellOri1 = sheetAttri.getCell(new Address(0, 0));
	    cellOri1.setContent(expression1);
	    Cell cellFim1 = sheetAttri.getCell(new Address(0, 1));

	    // test 2 -> test in cell f5, to modify cell c3 to be 3
	    String expression2 = "#c3:=4";
	    Cell cellOri2 = sheetAttri.getCell(new Address(5, 4));
	    cellOri2.setContent(expression2);
	    Cell cellFim2 = sheetAttri.getCell(new Address(2, 2));

	    // test 3 -> test in cell h3, to modify cell f2 to be 7
	    String expression3 = "#f2:=7";
	    Cell cellOri3 = sheetAttri.getCell(new Address(7, 2));
	    cellOri3.setContent(expression3);
	    Cell cellFim3 = sheetAttri.getCell(new Address(5, 1));

	    assertEquals("3", cellFim1.getContent());
	    assertEquals("4", cellFim2.getContent());
	    assertEquals("7", cellFim3.getContent());

	} catch (FormulaCompilationException e) {
	    fail("Exception error!");
	}
    }

    /**
     * Tests the sequence of expressions in the cell
     */
    @Test
    public void testSequence() {
	try {
	    String expression = "#{a1:=1;a2:=2;a3:=3;b1:=a1=1;a4:=SUM(a1:a3)}";
	    Cell cellOri1 = sheetSeq.getCell(new Address(0, 4));
	    cellOri1.setContent(expression);
	    Cell cellFim1_1 = sheetSeq.getCell(new Address(0, 0));
	    Cell cellFim1_2 = sheetSeq.getCell(new Address(0, 1));
	    Cell cellFim1_3 = sheetSeq.getCell(new Address(0, 2));
	    Cell cellFim1_4 = sheetSeq.getCell(new Address(0, 3));
	    Cell cellFim1_5 = sheetSeq.getCell(new Address(1, 0));
	    Value espected1 = new Value(6);
	    Value espected2 = new Value(true);

	    assertEquals("1", cellFim1_1.getContent());
	    assertEquals("2", cellFim1_2.getContent());
	    assertEquals("3", cellFim1_3.getContent());
	    assertEquals("6", cellFim1_4.getContent());
	    assertEquals(0, espected2.compareTo(cellFim1_5.getValue()));
	    assertEquals(0, espected1.compareTo(cellOri1.getValue()));

	} catch (FormulaCompilationException e) {
	    fail("Exception error!");
	}
    }
}
