package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.IType;

public interface IBinaryExpression extends IExpression{
	
	IType getArgumentsType();
	
	IExpression getLeftSide();
	boolean setLeftSide(IExpression newLeftSide);
	
	IExpression getRightSide();
	boolean setRightSide(IExpression newRightSide); 
	
}
