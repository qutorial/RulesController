package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;

public class NumericalLiteral implements ILiteralExpression {

	String mValue; 
			
	public IType getType() {
		return new NumericalType();
	}

	public Object getValue() {
		return mValue;
	}
	
	public boolean setValue(String val)
	{
		try {
			//Throws an exception if something is wrong with formatting
			Integer.parseInt(val);			
			mValue = val;
			return true;			
		} catch (Exception e) {
			//it's ok, trying double
		}
		
		try {
			//If something wrong formatted is passed throws an exception
			Double.parseDouble(val);			
			mValue = val;			
			return true;			
		} catch (Exception e) {
			return false;
		}
	}
	
}
