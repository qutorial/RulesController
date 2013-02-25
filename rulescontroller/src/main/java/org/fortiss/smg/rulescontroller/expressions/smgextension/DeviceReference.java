package org.fortiss.smg.rulescontroller.expressions.smgextension;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IBooleanDevice;
import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;
import org.fortiss.smg.rulescontroller.expressions.IExpression;
import org.fortiss.smg.rulescontroller.expressions.types.BooleanType;
import org.fortiss.smg.rulescontroller.expressions.types.IType;
import org.fortiss.smg.rulescontroller.expressions.types.NumericalType;

public class DeviceReference implements IExpression{

	IDevice mDevice;
	
	public DeviceReference(IDevice dev) {
		mDevice = dev;
	}
	
	@Override
	public IType getType() {
		if(mDevice instanceof IBooleanDevice)
		{
			return new BooleanType();
		}
		
		return new NumericalType();
	}

	@Override
	public String getUsualRepresentation() {
		return mDevice.getName();
	}

}
