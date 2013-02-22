package org.fortiss.smg.rulescontroller.smgextension;

import java.io.Serializable;
import java.util.Collection;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IBooleanDevice;
import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;
import org.fortiss.smg.rulescontroller.deviceinterfaces.INumericalDevice;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.AddDeviceException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.AddParameterException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.DeviceToAddIsNullException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.DuplicateDeviceException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.DuplicateDeviceNameException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.DuplicateNameException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.NullStringAsNameException;
import org.fortiss.utils.CollectionWrapper;

public class SmgEnvironment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7672863454577786096L;
	private CollectionWrapper<IDevice> mDevices;
	private CollectionWrapper<ParameterDeclaration> mParameters;

	public SmgEnvironment() {
		mDevices = new CollectionWrapper<IDevice>();
		mParameters = new CollectionWrapper<ParameterDeclaration>();
	}

	public Collection<IDevice> getAllDevices() {
		return mDevices.getCollection();
	}

	public Collection<ParameterDeclaration> getAllParameterDeclarations() {
		return mParameters.getCollection();
	}
	

	public BooleanParameterDeclaration addNewBooleanParameter(String name)
			throws AddParameterException {

		BooleanParameterDeclaration res = null;

		// throws
		checkName(name);

		res = new BooleanParameterDeclaration();
		res.setName(name);

		mParameters.getCollection().add(res);

		return res;
	}

	public NumericalParameterDeclaration addNewNumericalParameter(String name)
			throws AddParameterException {

		NumericalParameterDeclaration res = null;

		// throws
		checkName(name);

		res = new NumericalParameterDeclaration();
		res.setName(name);

		mParameters.getCollection().add(res);

		return res;
	}

	private void checkName(String name) throws NullStringAsNameException,
			DuplicateNameException {
		checkNameNull(name);
		checkNameDuplicates(name);
	}

	private void checkNameNull(String name) throws NullStringAsNameException {
		if (name == null) {
			throw new NullStringAsNameException();
		}
	}

	private void checkNameDuplicates(String name) throws DuplicateNameException {
		for (ParameterDeclaration param : mParameters.getCollection()) {
			if (param.getName().contentEquals(name)) {
				throw new DuplicateNameException();
			}
		}
	}

	public boolean addDevice(IDevice dev) throws AddDeviceException {
		if (dev == null) {
			throw new DeviceToAddIsNullException();
		}

		for (IDevice iDevice : mDevices.getCollection()) {
			if (dev == iDevice) {
				return true;
			}

			if (dev.getName() == iDevice.getName()) {
				throw new DuplicateDeviceNameException();
			}

			// Same id same measurement unit
			if (dev instanceof INumericalDevice) {
				if (iDevice instanceof INumericalDevice) {
					if (dev.getId().contentEquals(iDevice.getId())) {
						if (((INumericalDevice) dev).getMeasurementUnit() == ((INumericalDevice) iDevice)
								.getMeasurementUnit()) {
							throw new DuplicateDeviceException();
						}
					}
				}
			}

			// Same id boolean device
			if (dev instanceof IBooleanDevice) {
				if (iDevice instanceof IBooleanDevice) {
					if (dev.getId().contentEquals(iDevice.getId())) {
						throw new DuplicateDeviceException();
					}
				}
			}

		}
		
		mDevices.getCollection().add(dev);
		
		return true;
	}
}
