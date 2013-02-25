package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;

/**
 * @author zaur
 *
 *	Operates on numbers returns boolean
 *
 */
public abstract class ComparisonNumericalOperation extends BinaryExpressionBase {

	public IType getArgumentsType() {
		return new NumericalType();
	}

	public IType getType() {
		return new BooleanType();
	}

}
