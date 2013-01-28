package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;

public class BooleanParameterDeclaration extends ParameterDeclaration {
	
	public BooleanParameterDeclaration() {
		super(new BooleanType(), false);
	}
	
	public void setValue(boolean b)
	{
		super.setValue(b);
	}
	
	public void setValue(Boolean b)
	{
		super.setValue(b);
	}
	
	public boolean setValue(String val)
	{
		try
		{
			boolean tmp = Boolean.parseBoolean(val);
			super.setValue(tmp);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
