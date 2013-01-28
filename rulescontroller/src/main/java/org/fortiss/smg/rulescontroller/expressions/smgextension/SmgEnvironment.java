package org.fortiss.smg.rulescontroller.expressions.smgextension;

import java.util.ArrayList;
import java.util.List;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;
import org.fortiss.smg.rulescontroller.expressions.smgextension.exceptions.AddParameterException;
import org.fortiss.smg.rulescontroller.expressions.smgextension.exceptions.DuplicateNameException;
import org.fortiss.smg.rulescontroller.expressions.smgextension.exceptions.NullStringAsNameException;

public class SmgEnvironment {
	private ArrayList<IDevice> mDevices;
	private ArrayList<ParameterDeclaration> mParameters;

	public SmgEnvironment() {
		mDevices = new ArrayList<IDevice>();
		mParameters = new ArrayList<ParameterDeclaration>();
	}

	List<IDevice> getAllDevices() {
		List<IDevice> res = new ArrayList<IDevice>();
		res.addAll(mDevices);
		return res;
	}

	List<ParameterDeclaration> getAllParameterDeclarations() {
		List<ParameterDeclaration> res = new ArrayList<ParameterDeclaration>();
		res.addAll(mParameters);
		return res;
	}

	public BooleanParameterDeclaration addNewBooleanParameter(String name)
			throws AddParameterException {

		BooleanParameterDeclaration res = null;

		//throws
		checkName(name);

		res = new BooleanParameterDeclaration();
		res.setName(name);

		mParameters.add(res);

		return res;
	}

	public NumericalParameterDeclaration addNewNumericalParameter(String name)
			throws AddParameterException {
		
		NumericalParameterDeclaration res = null;
		
		//throws
		checkName(name);
		
		res = new NumericalParameterDeclaration();
		res.setName(name);
		
		mParameters.add(res);
		
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
		for (ParameterDeclaration param : mParameters) {
			if (param.getName().contentEquals(name)) {
				throw new DuplicateNameException();
			}
		}
	}

}
