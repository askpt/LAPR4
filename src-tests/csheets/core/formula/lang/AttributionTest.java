/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.lang;

import csheets.core.Value;
import csheets.core.formula.Expression;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaocarreira
 */
public class AttributionTest 
{
    
    public AttributionTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        System.out.println("Before class");
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
         System.out.println("Tear down");
    }
    
    @Before
    public void setUp() 
    {
        System.out.println("Before test");
    }
    
    @After
    public void tearDown()
    {
        System.out.println("After test\n");
    }

    /**
     * Test of applyTo method, of class Attribution.
     */
    @Test
    public void testApplyTo() throws Exception 
    {
      /*  System.out.println("applyTo");
        Expression leftOperand = null;
        Expression rightOperand = null;
        Attribution instance = new Attribution();
        Value expResult = null;
        Value result = instance.applyTo(leftOperand, rightOperand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        * */
    }

    /**
     * Test of getIdentifier method, of class Attribution.
     */
    @Test
    public void testGetIdentifier() 
    {
        /*
        System.out.println("getIdentifier");
        Attribution instance = new Attribution();
        String expResult = "";
        String result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        * */
    }

    /**
     * Test of getOperandValueType method, of class Attribution.
     */
    @Test
    public void testGetOperandValueType() 
    {
        /*
        System.out.println("getOperandValueType");
        Attribution instance = new Attribution();
        Value.Type expResult = null;
        Value.Type result = instance.getOperandValueType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        * */
    }
}