package org.fortiss.smg.rulescontroller.deviceinterfaces;

import java.io.Serializable;

/**
 * @author zaur
 *
 *	Interface, describing device in the smg
 *
 */
public interface IDevice extends Serializable{
	
	/**
	 * Gets identification string
	 * 
	 * @return unique in the system identificator
	 */
	String getId();
	
	String getName();
	
}
