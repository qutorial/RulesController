import static org.junit.Assert.assertTrue;

import org.fortiss.smg.rulescontroller.expressions.smgextension.BooleanParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.NumericalParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.ParameterReference;
import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParametersTypeSystemTest {

	static IType booleanType;
	static IType numericalType;

	@BeforeClass
	public static void testSetup() {
		booleanType = new BooleanType();
		numericalType = new NumericalType();


	}

	@AfterClass
	public static void testCleanup() {
		booleanType = null;
		numericalType = null;
	}

	@Test
	public void testBooleanParam()
	{
		BooleanParameterDeclaration bpd = new BooleanParameterDeclaration();
		assertTrue("Boolean parameter declaration takes value", bpd.setValue(false));
		
		ParameterReference bpr = new ParameterReference(bpd);
		
		assertTrue("Right type of reference", bpr.getType().equals(new BooleanType()));
		
		
		NumericalParameterDeclaration npd = new NumericalParameterDeclaration();
		assertTrue("Numerical parameter declaration takes value", npd.setValue(55));
		
		ParameterReference npr = new ParameterReference(npd);
		
		assertTrue("Right type of reference", npr.getType().equals(new NumericalType()));
		
		
		
		
	}
	
	
}
