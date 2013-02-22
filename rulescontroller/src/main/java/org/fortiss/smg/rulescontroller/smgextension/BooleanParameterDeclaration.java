package org.fortiss.smg.rulescontroller.smgextension;

import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;

public class BooleanParameterDeclaration extends ParameterDeclaration {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6503992201022953429L;

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
