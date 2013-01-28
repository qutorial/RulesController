package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;

/**
 * @author zaur
 *
 *	Operates on numbers returns number
 *
 */
public class BinaryNumericalOperation extends BinaryExpressionBase {

	public IType getArgumentsType() {
		return new NumericalType();
	}

	public IType getType() {
		return new NumericalType();
	}

}
