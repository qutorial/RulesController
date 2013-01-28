package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.expressions.types.IType;

class ParameterDeclaration {
	IType mType;
	Object mValue;	
	
	protected ParameterDeclaration(IType mType, Object mValue) {
		this.mType = mType;
		this.mValue = mValue;
	}
	
	public IType getType() {
		return mType;
	}
	
	protected void setType(IType mType) {
		this.mType = mType;
	}
	
	public Object getValue() {
		return mValue;
	}
	
	protected void setValue(Object mValue) {
		this.mValue = mValue;
	}	
}
