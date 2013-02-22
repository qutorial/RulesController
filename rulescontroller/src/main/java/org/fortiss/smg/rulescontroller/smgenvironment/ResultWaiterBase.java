package org.fortiss.smg.rulescontroller.smgenvironment;

public class ResultWaiterBase implements IResultWaiter {

	private volatile boolean mFinished;

	private boolean mResult;
	private Exception mException;

	public boolean check() {
		boolean fin = false;

		synchronized (this) {
			fin = mFinished;
		}

		return fin;
	}

	@Override
	public void notifyOk() {
		synchronized (this) {
			mResult = true;
			mFinished = true;
		}

	}

	@Override
	public void notifyFailed(Exception e) {
		synchronized (this) {
			mResult = false;
			mException = e;
			mFinished = true;
		}
	}
	
	public boolean getResult()
	{
		synchronized (this) {
			return mResult;
		}
	}

	public Exception getException()
	{
		synchronized (this) {
			return mException;
		}
	}
	
}
