package org.fortiss.smg.rulescontroller.smgenvironment;

public interface IResultWaiter {
	void notifyOk();
	void notifyFailed(Exception e);
}
