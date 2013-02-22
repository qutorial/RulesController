package org.fortiss.smg.rulescontroller.ruleimpl;

import java.io.Serializable;

import org.fortiss.smg.rulescontroller.expressions.IExpression;
import org.fortiss.smg.rulescontroller.ruleinterfaces.IElementaryCondition;
import org.fortiss.smg.rulescontroller.ruleinterfaces.ITimeInterval;

public class ElementaryCondition extends BooleanExpressionContainer implements
		IElementaryCondition, IExpression, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -431918177326324943L;

	@Override
	public ITimeInterval getTimeInterval() {
		// Tmp impl
		return new TimeInterval();
	}

}
