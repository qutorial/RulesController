package expressions.tests;
import static org.junit.Assert.*;

import org.fortiss.smg.rulescontroller.expressions.BooleanLiteral;
import org.fortiss.smg.rulescontroller.expressions.BracesExpression;
import org.fortiss.smg.rulescontroller.expressions.EqualsExpression;
import org.fortiss.smg.rulescontroller.expressions.GreaterOrEqualExpression;
import org.fortiss.smg.rulescontroller.expressions.IExpression;
import org.fortiss.smg.rulescontroller.expressions.MinusBinaryExpression;
import org.fortiss.smg.rulescontroller.expressions.NumericalLiteral;
import org.fortiss.smg.rulescontroller.expressions.PlusBinaryExpression;
import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;
import org.fortiss.smg.yices.YicesExpressionPrinter;
import org.fortiss.smg.yices.exceptions.NullExpressionException;
import org.fortiss.smg.yices.exceptions.PrinterException;
import org.fortiss.smg.yices.exceptions.UnhandledExpressionException;
import org.junit.BeforeClass;
import org.junit.Test;

public class YicesExpressionPrinterTest {

	static IType booleanType;
	static IType numericalType;
	static NumericalLiteral numberNine;
	static NumericalLiteral numberSevenPointFive;
	static BooleanLiteral trueLiteral;
	static BooleanLiteral falseLiteral;
	static IExpression sampleExpr;
	static YicesExpressionPrinter printer;

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

		// (9 + 7.5 >= 9 - 7.5) == true
		PlusBinaryExpression plus = new PlusBinaryExpression();
		plus.setLeftSide(numberNine);
		plus.setRightSide(numberSevenPointFive);
		MinusBinaryExpression minus = new MinusBinaryExpression();
		minus.setLeftSide(numberNine);
		minus.setRightSide(numberSevenPointFive);
		GreaterOrEqualExpression greq = new GreaterOrEqualExpression();
		greq.setLeftSide(plus);
		greq.setRightSide(minus);
		BracesExpression br = new BracesExpression();
		br.setExpression(greq);
		EqualsExpression eq = new EqualsExpression();
		eq.setLeftSide(br);
		eq.setRightSide(trueLiteral);

		sampleExpr = eq;
		
		printer = new YicesExpressionPrinter();
	}

	@Test
	public void test() {
		

		String s;
		try {
			s = printer.printExpression(sampleExpr);
		} catch (PrinterException e) {
			fail("Printing failed with exception: " + e.getClass().toString());
			return;
		}

		assertTrue("Printer works fine with a sample expression",
				s.contentEquals("(= (>= (+ 9 7.5) (- 9 7.5)) true)"));

	}
	

	 @Test(expected = NullExpressionException.class)
	 public void testNull() throws PrinterException
	 {
		 printer.printExpression(null);
	 }
	 
	 @Test(expected = NullExpressionException.class)
	 public void testNull2() throws PrinterException
	 {
		 PlusBinaryExpression plus = new PlusBinaryExpression();		 
		 plus.setLeftSide(numberNine);
		 //Right side is not set!		 
		 printer.printExpression(null);
	 }
	 
	 class UnexpectedExpression implements IExpression
	 {

		@Override
		public IType getType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getUsualRepresentation() {
			// TODO Auto-generated method stub
			return null;
		}
		 
	 }
	 
	 @Test(expected = UnhandledExpressionException.class)
	 public void testUnhandled() throws PrinterException
	 {
		 printer.printExpression(new UnexpectedExpression());
	 }
	 
}
