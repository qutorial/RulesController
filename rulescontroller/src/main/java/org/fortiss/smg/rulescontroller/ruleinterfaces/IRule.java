package org.fortiss.smg.rulescontroller.ruleinterfaces;

import java.io.Serializable;
import java.util.Collection;

public interface IRule extends Serializable {

	ICondition getCondition();
	void setCondition(ICondition cond);
	
	Collection<IConsequence> getConsequences();
	void addConsequence(IConsequence cons);
}
