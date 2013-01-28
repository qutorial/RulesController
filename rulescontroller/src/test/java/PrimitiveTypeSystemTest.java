import static org.junit.Assert.*;

import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PrimitiveTypeSystemTest {

	static IType booleanType;
	static IType booleanType2;
	static IType numericalType;
	static IType numericalType2;
	

	@BeforeClass
	public static void testSetup() {
		booleanType = new BooleanType();
		booleanType2 = new BooleanType();
		numericalType = new NumericalType();
		numericalType2 = new NumericalType();
	}

	@AfterClass
	public static void testCleanup() {
		booleanType = null;
		booleanType2 = null;
		numericalType = null;
		numericalType2 = null;
	}

	@Test
	public void testBoolean() {
		assertTrue("Type test boolean equal", booleanType.equals(booleanType2) );
	}
	
	@Test
	public void testNumerical()
	{
		assertTrue("Type test numerical equal", numericalType.equals(numericalType2) );
	}
	
	@Test
	public void testNumericalBoolean()
	{
		assertTrue("Type test numerical to boolean", !numericalType.equals(booleanType));
	}

}
