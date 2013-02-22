package org.fortiss.smg.rulescontroller.ruleinterfaces;

import java.io.Serializable;

import org.fortiss.smg.rulescontroller.expressions.IExpression;
import org.fortiss.smg.rulescontroller.rule.exceptions.SetExpressionException;

public interface IElementaryCondition extends Serializable {
	IExpression getExpression();
	void setExpression(IExpression expr) throws SetExpressionException;
	ITimeInterval getTimeInterval();	
}
