package org.fortiss.smg.rulescontroller.expressions;

public class EqualsExpression extends EqualityLikeComparison implements IBinaryExpression{

	@Override
	public String getUsualRepresentation() {
		return "=";
	}

}
