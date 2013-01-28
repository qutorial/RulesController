package org.fortiss.smg.rulescontroller.expressions;


/**
 * @author zaur
 *
 *	Checks types before setting right and left
 *
 */
public abstract class BinaryExpressionBase implements IBinaryExpression {

	IExpression mLeftSide;
	IExpression mRightSide;
	
	public BinaryExpressionBase() {
		mLeftSide = null;
		mRightSide = null;
	}

	public IExpression getLeftSide() {
		return mLeftSide;
	}

	public boolean setLeftSide(IExpression newLeftSide) {
		if(! newLeftSide.getType().equals(getArgumentsType()))
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
		if(! newRightSide.getType().equals(getArgumentsType()))
		{
			return false;
		}
		
		mRightSide = newRightSide;
		return true;
	}

	
}
