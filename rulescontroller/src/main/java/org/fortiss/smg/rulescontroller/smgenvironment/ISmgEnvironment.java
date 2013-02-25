package org.fortiss.smg.rulescontroller.smgenvironment;

import java.io.Serializable;
import java.util.Collection;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;
import org.fortiss.smg.rulescontroller.expressions.smgextension.BooleanParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.NumericalParameterDeclaration;
import org.fortiss.smg.rulescontroller.expressions.smgextension.ParameterDeclaration;
import org.fortiss.smg.rulescontroller.ruleinterfaces.IRule;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddDeviceException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddParameterException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddRuleException;

public interface ISmgEnvironment extends Serializable {

	public Collection<IDevice> getDevices();

	public Collection<ParameterDeclaration> getParameterDeclarations();

	public Collection<IRule> getRules();

	public BooleanParameterDeclaration addBooleanParameter(String name)
			throws AddParameterException;

	public NumericalParameterDeclaration addNumericalParameter(String name)
			throws AddParameterException;

	public boolean addDevice(IDevice dev) throws AddDeviceException;
	
	
	/**
	 * To be used with registered result waiter
	 * 
	 * @param rule
	 * @throws AddRuleException
	 */
	public boolean requestAddingRule(IRule rule, IResultWaiter r) throws AddRuleException;
}
