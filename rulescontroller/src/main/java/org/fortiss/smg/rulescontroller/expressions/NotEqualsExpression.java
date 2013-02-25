package org.fortiss.smg.rulescontroller.expressions;

public class NotEqualsExpression extends EqualityLikeComparison implements IBinaryExpression{

	@Override
	public String getUsualRepresentation() {
		return "/=";
	}
	
}
