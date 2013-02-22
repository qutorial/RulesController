package org.fortiss.smg.rulescontroller.smgenvironment;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IBooleanDevice;
import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;
import org.fortiss.smg.rulescontroller.deviceinterfaces.INumericalDevice;
import org.fortiss.smg.rulescontroller.ruleinterfaces.IRule;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddDeviceException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddParameterException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddRuleException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddingRuleInProgressException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.DeviceToAddIsNullException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.DuplicateDeviceException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.DuplicateDeviceNameException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.DuplicateNameException;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.NullStringAsNameException;
import org.fortiss.smg.rulescontroller.smgextension.BooleanParameterDeclaration;
import org.fortiss.smg.rulescontroller.smgextension.NumericalParameterDeclaration;
import org.fortiss.smg.rulescontroller.smgextension.ParameterDeclaration;
import org.fortiss.utils.CollectionWrapper;

public class SmgEnvironment implements Serializable, ISmgEnvironment {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7672863454577786096L;
	private CollectionWrapper<IDevice> mDevices;
	private CollectionWrapper<ParameterDeclaration> mParameters;
	private CollectionWrapper<IRule> mRules;
	private CollectionWrapper<IResultWaiter> mResultWaiters;

	private static ExecutorService executor;

	public SmgEnvironment() {
		mDevices = new CollectionWrapper<IDevice>();
		mParameters = new CollectionWrapper<ParameterDeclaration>();
		mRules = new CollectionWrapper<IRule>();
		mResultWaiters = new CollectionWrapper<IResultWaiter>();
	}

	@Override
	public Collection<IDevice> getDevices() {
		return mDevices.getCollection();
	}

	@Override
	public Collection<ParameterDeclaration> getParameterDeclarations() {
		return mParameters.getCollection();
	}

	@Override
	public BooleanParameterDeclaration addBooleanParameter(String name)
			throws AddParameterException {

		BooleanParameterDeclaration res = null;

		// throws
		checkName(name);

		res = new BooleanParameterDeclaration();
		res.setName(name);

		mParameters.getCollection().add(res);

		return res;
	}

	@Override
	public NumericalParameterDeclaration addNumericalParameter(String name)
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

	@Override
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

	@Override
	public Collection<IRule> getRules() {
		synchronized (mRules) {
			return mRules.getCollection();
		}
	}

	@Override
	public synchronized boolean requestAddingRule(IRule rule,
			IResultWaiter resultWaiter) throws AddRuleException {
		if (mResultWaiters.getCollection().size() > 0) {
			throw new AddingRuleInProgressException();
		} else {
			checkRuleAndAdd(rule, resultWaiter);
		}

		return true;
	}

	private class RuleCheckRunnable implements Runnable {
		private IRule mRule;
		private IResultWaiter mResultWaiter;

		public RuleCheckRunnable(IRule rule, IResultWaiter resultWaiter) {
			super();
			mRule = rule;
			mResultWaiter = resultWaiter;
		}

		@Override
		public void run() {

			boolean passed = true;
			
			
			

			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				passed = false;
			}

			if (!passed) {
				mResultWaiter.notifyFailed(new Exception("Check failed"));
				executor.shutdown();
				return;
			}

			synchronized (mRules) {
				mRules.getCollection().add(mRule);
			}

			mResultWaiter.notifyOk();
			executor.shutdown();

		}
	}

	private void checkRuleAndAdd(IRule rule, IResultWaiter resultWaiter) {
		executor = Executors.newFixedThreadPool(1);
		executor.execute(new RuleCheckRunnable(rule, resultWaiter));
	}

}
