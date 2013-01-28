package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;

public class BooleanParameterDeclaration extends ParameterDeclaration {
	
	public BooleanParameterDeclaration() {
		super(new BooleanType(), false);
	}
	
	public boolean setValue(boolean b)
	{
		return super.setValue(b);
	}
	
	public boolean setValue(Boolean b)
	{
		return super.setValue(b);
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
