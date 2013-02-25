package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;

public class BooleanLiteral implements ILiteralExpression{

	Boolean mValue;
	
	public BooleanLiteral() {
		mValue = false;
	}
	
	public IType getType() {
		return new BooleanType();
	}

	
	public void setValue(boolean b)
	{
		mValue = b;
	}
	
	public void setValue(Boolean b)
	{
		mValue = b;
	}
	
	public boolean setValue(String val)
	{
		try
		{
			boolean tmp = Boolean.parseBoolean(val);
			mValue = tmp;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public Boolean getBooleanValue()
	{
		return mValue;
	}


	public Object getValue() {
		return mValue;
	}

	@Override
	public String getUsualRepresentation() {
		return mValue.toString();
	}
}
