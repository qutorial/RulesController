package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.expressions.ILiteralExpression;
import org.fortiss.smg.rulescontroller.expressions.types.IType;

public class ParameterReference implements ILiteralExpression {

	ParameterDeclaration mDeclaration;
	
	public ParameterReference(ParameterDeclaration declaration) {
		mDeclaration = declaration;
	}
	
	public IType getType() {
		return mDeclaration.getType();
	}

	public Object getValue() {
		return mDeclaration.getValue();			
	}

}
