package org.fortiss.smg.rulescontroller.ruleinterfaces;

import java.io.Serializable;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IActuator;
import org.fortiss.smg.rulescontroller.expressions.IExpression;

public interface IConsequence extends Serializable {
	IActuator getActuator();
	IExpression getExpression();
}
