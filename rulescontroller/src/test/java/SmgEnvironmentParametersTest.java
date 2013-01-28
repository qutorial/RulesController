import static org.junit.Assert.fail;

import org.fortiss.smg.rulescontroller.expressions.smgextension.BooleanParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.NumericalParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.SmgEnvironment;
import org.fortiss.smg.rulescontroller.expressions.smgextension.exceptions.AddParameterException;
import org.fortiss.smg.rulescontroller.expressions.smgextension.exceptions.DuplicateNameException;
import org.fortiss.smg.rulescontroller.expressions.smgextension.exceptions.NullStringAsNameException;
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
			bpd = Environment.addNewBooleanParameter("booleanPar");
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
			npd = Environment.addNewNumericalParameter("numericalPar");
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
		Environment.addNewBooleanParameter(null);
	}
	
	@Test (expected = NullStringAsNameException.class)
	public void testNullNum() throws Exception
	{
		Environment.addNewNumericalParameter(null);
	}
	
	
	@Test (expected = DuplicateNameException.class)
	public void testDup1() throws Exception
	{
		Environment.addNewNumericalParameter("par");
		Environment.addNewNumericalParameter("par");
	}
	
	@Test (expected = DuplicateNameException.class)
	public void testDup2() throws Exception
	{
		Environment.addNewBooleanParameter("parb");
		Environment.addNewBooleanParameter("parb");
	}
	
	@Test (expected = DuplicateNameException.class)
	public void testDup3() throws Exception
	{
		Environment.addNewBooleanParameter("parc");
		Environment.addNewNumericalParameter("parc");
	}
	
	@Test (expected = DuplicateNameException.class)
	public void testDup4() throws Exception
	{
		Environment.addNewNumericalParameter("pard");
		Environment.addNewBooleanParameter("pard");		
	}
	
	

}
