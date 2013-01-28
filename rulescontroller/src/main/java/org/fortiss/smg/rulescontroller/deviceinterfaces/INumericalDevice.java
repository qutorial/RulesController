package org.fortiss.smg.rulescontroller.deviceinterfaces;


/**
 * @author zaur
 *
 * Device which has number values.
 *
 */
public interface INumericalDevice extends IDevice {
	EMeasurementUnit getMeasurementUnit();
	double getMinimalValue();
	double getMaximalValue();
}
