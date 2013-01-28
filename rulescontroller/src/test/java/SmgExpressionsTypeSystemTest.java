import static org.junit.Assert.*;

import org.fortiss.smg.rulescontroller.expressions.BooleanLiteral;
import org.fortiss.smg.rulescontroller.expressions.NumericalLiteral;
import org.fortiss.smg.rulescontroller.expressions.smgextension.BooleanParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.ParameterReference;
import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SmgExpressionsTypeSystemTest {

	static IType booleanType;
	static IType numericalType;
	static NumericalLiteral numberNine;
	static NumericalLiteral numberSevenPointFive;
	static BooleanLiteral trueLiteral;
	static BooleanLiteral falseLiteral;

	@BeforeClass
	public static void testSetup() {
		booleanType = new BooleanType();
		numericalType = new NumericalType();

		NumericalLiteral numberNineVal = new NumericalLiteral();
		numberNineVal.setValue("9");
		numberNine = numberNineVal;

		NumericalLiteral numberSevenPointFiveVal = new NumericalLiteral();
		numberSevenPointFiveVal.setValue("7.5");
		numberSevenPointFive = numberSevenPointFiveVal;

		BooleanLiteral trueLiteralVal = new BooleanLiteral();
		trueLiteralVal.setValue("true");
		trueLiteral = trueLiteralVal;

		BooleanLiteral falseLiteralVal = new BooleanLiteral();
		falseLiteralVal.setValue("false");
		falseLiteral = falseLiteralVal;
	}

	@AfterClass
	public static void testCleanup() {
		booleanType = null;
		numericalType = null;
		numberNine = null;
		numberSevenPointFive = null;
		trueLiteral = null;
	}

	@Test
	public void testBooleanParam()
	{
		BooleanParameterDeclaration bpd = new BooleanParameterDeclaration();
		bpd.setValue(false);
		
		ParameterReference bpr = new ParameterReference(bpd);
		
		assertTrue("Right type of reference", bpr.getType().equals(new BooleanType()));			
	}
	
	
}
