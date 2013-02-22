package org.fortiss.smg.rulescontroller.ruleimpl;

import java.io.Serializable;
import java.util.Collection;

import org.fortiss.smg.rulescontroller.ruleinterfaces.ICondition;
import org.fortiss.smg.rulescontroller.ruleinterfaces.IConsequence;
import org.fortiss.smg.rulescontroller.ruleinterfaces.IRule;
import org.fortiss.utils.CollectionWrapper;

public class Rule implements Serializable, IRule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3996119118971079545L;
	ICondition mCondition;
	CollectionWrapper<IConsequence> mConsequences;

	public Rule() {
		mConsequences = new CollectionWrapper<IConsequence>();
	}
	
	@Override
	public ICondition getCondition() {
		return mCondition;
	}

	@Override
	public void setCondition(ICondition cond) {
		mCondition = cond;
	}

	@Override
	public Collection<IConsequence> getConsequences() {
		return mConsequences.getCollection();
	}
	
	@Override
	public void addConsequence(IConsequence cons) {
		mConsequences.getCollection().add(cons);
	}
	
	

}
