package org.fortiss.smg.rulescontroller.expressions;

public interface ILiteralExpression extends IExpression {
	
	/**
	 * @return
	 * 
	 * Should be String or Double, Integer, so on.
	 * 
	 */
	Object getValue();
}
