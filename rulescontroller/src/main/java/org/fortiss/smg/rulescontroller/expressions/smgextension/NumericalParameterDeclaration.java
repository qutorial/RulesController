package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;

public class NumericalParameterDeclaration extends ParameterDeclaration {

	public NumericalParameterDeclaration() {
		super(new NumericalType(), 0);
	}

	public boolean setValue(String val) {
		try {
			// Throws an exception if something is wrong with formatting
			int v = Integer.parseInt(val);
			super.setValue(v);
			return true;
		} catch (Exception e) {
			// it's ok, trying double
		}

		try {
			// If something wrong formatted is passed throws an exception
			double d = Double.parseDouble(val);
			super.setValue(d);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
	
	public boolean setValue(int val)
	{
		super.setValue(val);
		return true;
	}
	
	public boolean setValue(double val)
	{
		super.setValue(val);
		return true;
	}
}
