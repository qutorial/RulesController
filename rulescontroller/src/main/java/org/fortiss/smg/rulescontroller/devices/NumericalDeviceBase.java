package org.fortiss.smg.rulescontroller.devices;


import org.fortiss.smg.rulescontroller.deviceinterfaces.EMeasurementUnit;
import org.fortiss.smg.rulescontroller.deviceinterfaces.INumericalDevice;
import org.fortiss.smg.rulescontroller.devices.exceptions.WrongMinMaxValuesException;

public abstract class NumericalDeviceBase extends DeviceBase implements INumericalDevice{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5678880380475053792L;
	private EMeasurementUnit mMeasurementUnit;
	private double mMinimalValue;
	private double mMaximalValue;
	
	public NumericalDeviceBase() {
		mMeasurementUnit = EMeasurementUnit.DEGREES_CELCIUS;
		mMinimalValue = 0;
		mMaximalValue = 100;
	}
	
	public EMeasurementUnit getMeasurementUnit() {
		return mMeasurementUnit;
	}
	
	public double getMinimalValue() {
		return mMinimalValue;
	}
	
	public double getMaximalValue() {
		return mMaximalValue;
	}
	
	public void setMeasurementUnit(EMeasurementUnit measurementUnit) {
		mMeasurementUnit = measurementUnit;
	}
	
	public void setMinMax(double min, double max) throws WrongMinMaxValuesException
	{
		if(min <= max)
		{
			mMinimalValue = min;
			mMaximalValue = max;
			return;
		}
		
		throw new WrongMinMaxValuesException();
	}
	
		
}
