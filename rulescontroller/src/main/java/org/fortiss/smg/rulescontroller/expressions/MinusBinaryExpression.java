package org.fortiss.smg.rulescontroller.expressions;


/**
 * @author zaur
 *	
 *	Regular minus
 */
public class MinusBinaryExpression extends BinaryNumericalOperation implements IBinaryExpression{

	@Override
	public String getUsualRepresentation() {
		return "-";
	}
	
}
