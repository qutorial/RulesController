import static org.junit.Assert.assertTrue;

import org.fortiss.smg.rulescontroller.expressions.BooleanLiteral;
import org.fortiss.smg.rulescontroller.expressions.BracesExpression;
import org.fortiss.smg.rulescontroller.expressions.EqualsExpression;
import org.fortiss.smg.rulescontroller.expressions.GreaterExpression;
import org.fortiss.smg.rulescontroller.expressions.GreaterOrEqualExpression;
import org.fortiss.smg.rulescontroller.expressions.LessExpression;
import org.fortiss.smg.rulescontroller.expressions.LessOrEqualExpression;
import org.fortiss.smg.rulescontroller.expressions.MinusBinaryExpression;
import org.fortiss.smg.rulescontroller.expressions.NumericalLiteral;
import org.fortiss.smg.rulescontroller.expressions.PlusBinaryExpression;
import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LiteralAndExpressionsTypeSystemTest {

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
	public void testNumericalLiterals() {
		assertTrue("Testing value 9",
				numberNine.getValue().toString().equals("9"));
		assertTrue("Testing value 7.5", numberSevenPointFive.getValue()
				.toString().equals("7.5"));
		assertTrue("Testing value true", trueLiteral.getValue().toString()
				.equals("true"));
	}

	@Test
	public void testComparisonsLess() {
		LessExpression lexpr = new LessExpression();
		assertTrue("LessExpression expression type",
				lexpr.getType().equals(new BooleanType()));

		assertTrue("LessExpression: setting left side",
				lexpr.setLeftSide(numberSevenPointFive));
		assertTrue("LessExpression: setting right side",
				lexpr.setRightSide(numberNine));

		assertTrue("LessExpression: setting left side wrongly",
				!lexpr.setLeftSide(trueLiteral));

	}

	@Test
	public void testComparisonsLessEq() {
		LessOrEqualExpression leqexpr = new LessOrEqualExpression();
		assertTrue("LessOrEqualExpression expression type", leqexpr.getType()
				.equals(new BooleanType()));

		assertTrue("LessOrEqualExpression: setting left side",
				leqexpr.setLeftSide(numberSevenPointFive));
		assertTrue("LessOrEqualExpression: setting right side",
				leqexpr.setRightSide(numberNine));

		assertTrue("LessOrEqualExpression: setting left side wrongly",
				!leqexpr.setLeftSide(trueLiteral));

	}

	@Test
	public void testComparisonsGreater() {
		GreaterExpression greaterExpre = new GreaterExpression();
		assertTrue("GreaterExpression expression type", greaterExpre.getType()
				.equals(new BooleanType()));

		assertTrue("GreaterExpression: setting left side",
				greaterExpre.setLeftSide(numberSevenPointFive));
		assertTrue("GreaterExpression: setting right side",
				greaterExpre.setRightSide(numberNine));

		assertTrue("GreaterExpression: setting left side wrongly",
				!greaterExpre.setLeftSide(trueLiteral));

	}

	@Test
	public void testComparisonsGreaterEq() {
		GreaterOrEqualExpression greqexpr = new GreaterOrEqualExpression();
		assertTrue("GreaterOrEqualExpression expression type", greqexpr
				.getType().equals(new BooleanType()));

		assertTrue("GreaterOrEqualExpression: setting left side",
				greqexpr.setLeftSide(numberSevenPointFive));
		assertTrue("GreaterOrEqualExpression: setting right side",
				greqexpr.setRightSide(numberNine));

		assertTrue("GreaterOrEqualExpression: setting left side wrongly",
				!greqexpr.setLeftSide(trueLiteral));
	}

	@Test
	public void equalsExpressionTest() {
		EqualsExpression eq = new EqualsExpression();
		assertTrue("EqualsExpression: initially type is null",
				eq.getType() == null);

		assertTrue("EqualsExpression: setting left side succeeds",
				eq.setLeftSide(numberNine));

		assertTrue(
				"EqualsExpression: after setting numerical left side, type is numerical",
				eq.getType().equals(new NumericalType()));

		assertTrue("EqualsExpression:  setting right side wrong",
				!eq.setRightSide(trueLiteral));

		assertTrue("EqualsExpression: setting right side succeeds",
				eq.setLeftSide(numberSevenPointFive));

	}

	@Test
	public void notEqualsExpressionTest() {
		EqualsExpression eq = new EqualsExpression();
		assertTrue("EqualsExpression: initially type is null",
				eq.getType() == null);

		assertTrue("EqualsExpression: setting left side succeeds",
				eq.setLeftSide(falseLiteral));

		assertTrue(
				"EqualsExpression: after setting numerical left side, type is boolean",
				eq.getType().equals(new BooleanType()));

		assertTrue("EqualsExpression:  setting right side wrong",
				!eq.setRightSide(numberSevenPointFive));

		assertTrue("EqualsExpression: setting right side succeeds",
				eq.setLeftSide(trueLiteral));

	}

	@Test
	public void compositionTestOne() {
		// (9 + 7.5 >= 9 - 7.5) == true
		PlusBinaryExpression plus = new PlusBinaryExpression();

		assertTrue("PlusBinaryExpression: type is numerical", plus.getType()
				.equals(new NumericalType()));		
		assertTrue("Setting lhs PlusBinaryExpression", plus.setLeftSide(numberNine));
		assertTrue("Setting rhs PlusBinaryExpression", plus.setRightSide(numberSevenPointFive));
		
		
		MinusBinaryExpression minus = new MinusBinaryExpression();

		assertTrue("MinusBinaryExpression: type is numerical", minus.getType()
				.equals(new NumericalType()));
		assertTrue("Setting lhs MinusBinaryExpression", minus.setLeftSide(numberNine));
		assertTrue("Setting rhs MinusBinaryExpression", minus.setRightSide(numberSevenPointFive));

		
		GreaterOrEqualExpression greq = new GreaterOrEqualExpression();
		assertTrue("Greater equals has boolean type", greq.getType().equals(new BooleanType()));
		
		assertTrue("Plus goes well in comparison", greq.setLeftSide(plus));
		assertTrue("Minus goes well in comparison", greq.setRightSide(minus));
		
		BracesExpression br = new BracesExpression();
		br.setExpression(greq);
		
		EqualsExpression eq = new EqualsExpression();
		assertTrue("Braces go well in EqualsExpression", eq.setLeftSide(br));
		assertTrue("True literal goes well in EqualsExpression", eq.setRightSide(trueLiteral));		
		
		assertTrue("EqualsExpression has boolean type", eq.getType().equals(new BooleanType()));
		
	}

}
