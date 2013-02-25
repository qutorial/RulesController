package org.fortiss.smg.rulescontroller.expressions.smgextension;

import java.io.Serializable;

import org.fortiss.smg.rulescontroller.expressions.types.IType;

public abstract class ParameterDeclaration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8184401302153372105L;
	IType mType;
	Object mValue;	
	String mName;
	
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
	
	protected boolean setValue(Object mValue) {
		this.mValue = mValue;
		return true;		
	}	
	
	public String getName()
	{
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	
}
