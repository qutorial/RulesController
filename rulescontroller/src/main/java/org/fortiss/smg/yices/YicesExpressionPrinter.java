package org.fortiss.smg.yices;

import org.fortiss.smg.rulescontroller.expressions.BracesExpression;
import org.fortiss.smg.rulescontroller.expressions.IBinaryExpression;
import org.fortiss.smg.rulescontroller.expressions.IExpression;
import org.fortiss.smg.rulescontroller.expressions.ILiteralExpression;
import org.fortiss.smg.rulescontroller.expressions.smgextension.DeviceReference;
import org.fortiss.smg.rulescontroller.expressions.smgextension.ParameterReference;
import org.fortiss.smg.yices.exceptions.NullExpressionException;
import org.fortiss.smg.yices.exceptions.PrinterException;
import org.fortiss.smg.yices.exceptions.UnhandledExpressionException;

public class YicesExpressionPrinter {

	public String printExpression(IExpression expr) throws PrinterException {
		String res = printExpressionPrivate(expr);		
		return res;		
	}

	private String inBraces(String s)
	{
		return "(" + s + ")";
	}
	
	String printExpressionPrivate(IExpression expr) throws PrinterException {
		
		if(expr == null)
		{
			throw new NullExpressionException();
		}
		
		if (expr instanceof BracesExpression) {
			return printExpressionPrivate(((BracesExpression) expr)
					.getExpression());
		}

		if (expr instanceof DeviceReference
				|| expr instanceof ParameterReference || expr instanceof ILiteralExpression) {
			return expr.getUsualRepresentation();
		}
		
		if(expr instanceof IBinaryExpression)
		{
			IBinaryExpression bexpr = (IBinaryExpression) expr;
			String lhs = printExpressionPrivate(bexpr.getLeftSide());
			String rhs = printExpressionPrivate(bexpr.getRightSide());
			String res = bexpr.getUsualRepresentation() +" "+  lhs + " " + rhs;
			return inBraces(res);			
		}

		throw new UnhandledExpressionException();
	}

}
