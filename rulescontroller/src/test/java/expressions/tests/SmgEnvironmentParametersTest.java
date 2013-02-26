package expressions.tests;
import static org.junit.Assert.fail;

import org.fortiss.smg.rulescontroller.expressions.smgextension.BooleanParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.NumericalParameterDeclaration;
import org.fortiss.smg.rulescontroller.smgenvironment.SmgEnvironment;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddParameterException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.DuplicateNameException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.NullStringAsNameException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SmgEnvironmentParametersTest {

	public static SmgEnvironment Environment;

	@BeforeClass
	public static void testSetup() {
		Environment = new SmgEnvironment();
	}

	@AfterClass
	public static void testCleanup() {
		Environment = null;
	}

	@Test
	public void testBoolean() {
		BooleanParameterDeclaration bpd = null;
		try {
			bpd = Environment.addBooleanParameter("booleanPar");
		} catch (AddParameterException e) {
			fail("Add boolean parameter has thrown unexpected exception"
					+ e.getMessage());
		}
		
		if(bpd == null)
		{
			fail("Add boolean parameter returned null");
		}			
	}
	
	@Test
	public void testNumerical() {
		NumericalParameterDeclaration npd = null;
		try {
			npd = Environment.addNumericalParameter("numericalPar");
		} catch (AddParameterException e) {
			fail("Add numerical parameter has thrown unexpected exception"
					+ e.getMessage());
		}
		
		if(npd == null)
		{
			fail("Add numerical parameter returned null");
		}			
	}
	
	@Test (expected = NullStringAsNameException.class)
	public void testNull() throws Exception
	{
		Environment.addBooleanParameter(null);
	}
	
	@Test (expected = NullStringAsNameException.class)
	public void testNullNum() throws Exception
	{
		Environment.addNumericalParameter(null);
	}
	
	
	@Test (expected = DuplicateNameException.class)
	public void testDup1() throws Exception
	{
		Environment.addNumericalParameter("par");
		Environment.addNumericalParameter("par");
	}
	
	@Test (expected = DuplicateNameException.class)
	public void testDup2() throws Exception
	{
		Environment.addBooleanParameter("parb");
		Environment.addBooleanParameter("parb");
	}
	
	@Test (expected = DuplicateNameException.class)
	public void testDup3() throws Exception
	{
		Environment.addBooleanParameter("parc");
		Environment.addNumericalParameter("parc");
	}
	
	@Test (expected = DuplicateNameException.class)
	public void testDup4() throws Exception
	{
		Environment.addNumericalParameter("pard");
		Environment.addBooleanParameter("pard");		
	}
	
	

}
