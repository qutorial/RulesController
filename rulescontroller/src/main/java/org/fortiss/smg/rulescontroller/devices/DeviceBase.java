package org.fortiss.smg.rulescontroller.devices;

import org.fortiss.smg.rulescontroller.deviceinterfaces.IDevice;

public abstract class DeviceBase implements IDevice{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1736900540455511833L;
	
	private String mId;
	private String mName;
	
	public DeviceBase() {
		mId = "";
		mName = "";		
	}
	
	public String getId() {
		return mId;
	}
	
	public String getName() {
		return mName;
	}

	public void setId(String id) {
		mId = id;
	}
	
	//TODO Add test if it is like id name	
	public void setName(String name) {
		mName = name;
	}
}
