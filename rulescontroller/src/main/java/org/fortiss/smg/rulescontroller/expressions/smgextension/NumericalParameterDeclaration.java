package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;

public class NumericalParameterDeclaration extends ParameterDeclaration {

	protected NumericalParameterDeclaration() {
		super(new NumericalType(), 0);
	}

	public boolean setValue(String val) {
		try {
			// Throws an exception if something is wrong with formatting
			Integer.parseInt(val);
			super.setValue(val);
			return true;
		} catch (Exception e) {
			// it's ok, trying double
		}

		try {
			// If something wrong formatted is passed throws an exception
			Double.parseDouble(val);
			super.setValue(val);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
}
