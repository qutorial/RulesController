package org.fortiss.smg.rulescontroller.ruleimpl;

import java.io.Serializable;

import org.fortiss.smg.rulescontroller.expressions.IExpression;
import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.rule.exceptions.NullExpressionException;
import org.fortiss.smg.rulescontroller.rule.exceptions.SetExpressionException;
import org.fortiss.smg.rulescontroller.rule.exceptions.WrongExpressionTypeException;

public class BooleanExpressionContainer implements IExpression, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5495021019983491346L;
	private IExpression mExpression;

	public IExpression getExpression() {
		return mExpression;
	}

	public void setExpression(IExpression expr) throws SetExpressionException {
		if(expr == null)
		{
			throw new NullExpressionException();
		}
		if(! expr.getType().equals(new BooleanType()))
		{
			throw new WrongExpressionTypeException();
		}
		
		mExpression = expr;		
	}

	@Override
	public IType getType() {
		return new BooleanType();
	}

	@Override
	public String getUsualRepresentation() {
		return mExpression.getUsualRepresentation();
	}
}
