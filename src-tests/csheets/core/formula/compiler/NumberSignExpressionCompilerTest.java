package csheets.core.formula.compiler;

import static org.junit.Assert.assertEquals;

import org.junit.*;

/**
 * JUnit teste on NumberSignExpressionCompiler
 * 
 * @author Joao Carreira
 */
public class NumberSignExpressionCompilerTest {

    public NumberSignExpressionCompilerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("Before class");
    }

    @AfterClass
    public static void tearDownClass() {
	System.out.println("Tear down");
    }

    @Before
    public void setUp() {
	System.out.println("Before test");
    }

    @After
    public void tearDown() {
	System.out.println("After test\n");
    }

    /**
     * Test of getStarter method, of class NumberSignExpressionCompiler.
     */
    @Test
    public void testGetStarter() {
	System.out.println("getStarter");
	NumberSignExpressionCompiler instance = new NumberSignExpressionCompiler();
	char expResult = '#';
	char result = instance.getStarter();
	assertEquals(expResult, result);
    }

    /**
     * Test of compile method, of class NumberSignExpressionCompiler.
     */
    @Test
    public void testCompile() throws Exception {
	/*
	 * System.out.println("compile"); Cell cell = null; String source = "";
	 * NumberSignExpressionCompiler instance = new
	 * NumberSignExpressionCompiler(); Expression expResult = null;
	 * Expression result = instance.compile(cell, source);
	 * assertEquals(expResult, result);
	 * fail("The test case is a prototype.");
	 */
    }

    /**
     * Test of convert method, of class NumberSignExpressionCompiler.
     */
    @Test
    public void testConvert() throws Exception {
	/*
	 * System.out.println("convert"); Cell cell = null; AST node = null;
	 * NumberSignExpressionCompiler instance = new
	 * NumberSignExpressionCompiler(); Expression expResult = null;
	 * Expression result = instance.convert(cell, node);
	 * assertEquals(expResult, result);
	 * fail("The test case is a prototype.");
	 */
    }
}