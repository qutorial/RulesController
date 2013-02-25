package org.fortiss.smg.rulescontroller.expressions;

import org.fortiss.smg.rulescontroller.expressions.types.IType;

public abstract class EqualityLikeComparison implements IBinaryExpression {

	IExpression mRightSide;
	IExpression mLeftSide;

	public EqualityLikeComparison() {
		mRightSide = null;
		mLeftSide = null;
	}

	public IType getType() {
		if (mRightSide != null) {
			return mRightSide.getType();
		}

		if (mLeftSide != null) {
			return mLeftSide.getType();
		}

		return null;
	}

	public IType getArgumentsType() {
		return getType();
	}

	public IExpression getLeftSide() {
		return mLeftSide;
	}

	public boolean setLeftSide(IExpression newLeftSide) {
		if(!checkType(newLeftSide))
		{
			return false;
		}

		mLeftSide = newLeftSide;
		return true;
	}

	public IExpression getRightSide() {
		return mRightSide;
	}

	public boolean setRightSide(IExpression newRightSide) {
		if(!checkType(newRightSide))
		{
			return false;
		}

		mRightSide = newRightSide;
		return true;
	}

	private boolean checkType(IExpression expr) {
		if (getArgumentsType() != null) {
			if (!getArgumentsType().equals(expr.getType())) {
				return false;
			}
		}
		
		return true;
	}

}
