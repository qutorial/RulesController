package org.fortiss.smg.rulescontroller.smgextension;

import java.io.Serializable;
import java.util.Collection;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.AddDeviceException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.AddParameterException;

public interface ISmgEnvironment extends Serializable {

	public Collection<IDevice> getAllDevices();

	public Collection<ParameterDeclaration> getAllParameterDeclarations();
	

	public BooleanParameterDeclaration addNewBooleanParameter(String name)
			throws AddParameterException;

	public NumericalParameterDeclaration addNewNumericalParameter(String name)
			throws AddParameterException;

	public boolean addDevice(IDevice dev) throws AddDeviceException;
}
