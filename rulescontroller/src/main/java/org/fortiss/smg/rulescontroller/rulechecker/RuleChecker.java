package org.fortiss.smg.rulescontroller.rulechecker;

import org.fortiss.smg.rulescontroller.ruleinterfaces.IRule;
import org.fortiss.smg.rulescontroller.smgenvironment.ISmgEnvironment;

public class RuleChecker {
	public class CheckResult
	{
		public boolean succeeded;
		public Exception exception;
	}
	
	public CheckResult checkRuleInEnvironment(ISmgEnvironment environment, IRule rule)
	{
		// TODO : Continue here
		return null;
	}
	
}
