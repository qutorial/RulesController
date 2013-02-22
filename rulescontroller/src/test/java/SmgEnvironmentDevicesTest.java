import static org.junit.Assert.fail;

import org.fortiss.smg.rulescontroller.deviceinterfaces.EMeasurementUnit;
import org.fortiss.smg.rulescontroller.devices.BooleanActuator;
import org.fortiss.smg.rulescontroller.devices.NumericalActuator;
import org.fortiss.smg.rulescontroller.devices.NumericalSensor;
import org.fortiss.smg.rulescontroller.smgextension.SmgEnvironment;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.AddDeviceException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.DuplicateDeviceException;
import org.fortiss.smg.rulescontroller.smgextension.exceptions.DuplicateDeviceNameException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SmgEnvironmentDevicesTest {

	public static SmgEnvironment Environment;

	@BeforeClass
	public static void testSetup() {
		Environment = new SmgEnvironment();
	}

	@AfterClass
	public static void testCleanup() {
		Environment = null;
	}

	@Test
	public void testBoolean() throws AddDeviceException {
		BooleanActuator acSwitch = new BooleanActuator();
		acSwitch.setId("enocean?something");
		acSwitch.setName("ACSwitch");

		int n = Environment.getAllDevices().size();

		boolean res = Environment.addDevice(acSwitch);

		if (!res) {
			fail("Fails to add a device");
		}

		if (n != Environment.getAllDevices().size() - 1) {
			fail("The device has not been really added");
		}
	}

	@Test(expected = DuplicateDeviceException.class)
	public void testDuplication() throws AddDeviceException {
		BooleanActuator acSwitch = new BooleanActuator();
		acSwitch.setId("enocean?something1");
		acSwitch.setName("ACSwitch1");

		Environment.addDevice(acSwitch);

		BooleanActuator acSwitch1 = new BooleanActuator();
		acSwitch1.setId("enocean?something1");
		acSwitch1.setName("ACSwitch2");

		Environment.addDevice(acSwitch1);

	}

	@Test(expected = DuplicateDeviceNameException.class)
	public void testDuplicationName() throws AddDeviceException {
		BooleanActuator acSwitch = new BooleanActuator();
		acSwitch.setId("enocean?something1");
		acSwitch.setName("ACSwitch1");

		Environment.addDevice(acSwitch);

		BooleanActuator acSwitch1 = new BooleanActuator();
		acSwitch1.setId("enocean?something2");
		acSwitch1.setName("ACSwitch1");

		Environment.addDevice(acSwitch1);

	}

	@Test
	public void testAddingNum() throws AddDeviceException {
		NumericalSensor temp = new NumericalSensor();
		temp.setId("enocean?temp123");
		temp.setName("Temperature");
		temp.setMeasurementUnit(EMeasurementUnit.DEGREES_CELCIUS);

		int n = Environment.getAllDevices().size();

		boolean res = Environment.addDevice(temp);

		if (!res) {
			fail("Fails to add a device");
		}

		if (n != Environment.getAllDevices().size() - 1) {
			fail("The device has not been really added");
		}
	}

	// The same but another measurement unit
	// Should be no problem
	@Test
	public void testAddingNumDupli() throws AddDeviceException {
		NumericalSensor temp = new NumericalSensor();
		temp.setId("enocean?temp123");
		temp.setName("Light");
		temp.setMeasurementUnit(EMeasurementUnit.LUX);

		int n = Environment.getAllDevices().size();

		boolean res = Environment.addDevice(temp);

		if (!res) {
			fail("Fails to add a device");
		}

		if (n != Environment.getAllDevices().size() - 1) {
			fail("The device has not been really added");
		}
	}

	// Must fail
	@Test(expected = DuplicateDeviceException.class)
	public void testAddingNumDuplicationFail() throws AddDeviceException {
		NumericalSensor temp = new NumericalSensor();
		temp.setId("enocean?temp123111");
		temp.setName("Temperature2");
		temp.setMeasurementUnit(EMeasurementUnit.DEGREES_CELCIUS);

		int n = Environment.getAllDevices().size();

		boolean res = Environment.addDevice(temp);

		if (!res) {
			fail("Fails to add a device");
		}

		if (n != Environment.getAllDevices().size() - 1) {
			fail("The device has not been really added");
		}

		NumericalSensor temp1 = new NumericalSensor();
		temp1.setId("enocean?temp123111");
		temp1.setName("Temperature3");
		temp1.setMeasurementUnit(EMeasurementUnit.DEGREES_CELCIUS);

		Environment.addDevice(temp1);
	}

	// Also must fail
	@Test(expected = DuplicateDeviceException.class)
	public void testAddingNumDuplicationFail2() throws AddDeviceException {
		NumericalSensor temp = new NumericalSensor();
		temp.setId("enocean?temp12311122");
		temp.setName("Temperature222");
		temp.setMeasurementUnit(EMeasurementUnit.DEGREES_CELCIUS);

		int n = Environment.getAllDevices().size();

		boolean res = Environment.addDevice(temp);

		if (!res) {
			fail("Fails to add a device");
		}

		if (n != Environment.getAllDevices().size() - 1) {
			fail("The device has not been really added");
		}

		NumericalActuator temp1 = new NumericalActuator();
		temp1.setId("enocean?temp12311122");
		temp1.setName("Temperature322");
		temp1.setMeasurementUnit(EMeasurementUnit.DEGREES_CELCIUS);

		Environment.addDevice(temp1);
	}

}
