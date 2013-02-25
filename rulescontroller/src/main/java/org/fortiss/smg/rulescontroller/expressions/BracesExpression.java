package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.IType;

public class BracesExpression implements IExpression{
	IExpression mExpression;

	public BracesExpression() {
		mExpression = null;
	}
	
	public IType getType() {
		if(mExpression == null)
		{
			return null;
		}
		return mExpression.getType();
	}
	
	public void setExpression(IExpression expr)
	{
		mExpression = expr;
	}
	
	public IExpression getExpression()
	{
		return mExpression;
	}

	@Override
	public String getUsualRepresentation() {
		return "()";
	}
}
