package org.fortiss.utils;

import java.util.ArrayList;
import java.util.Collection;

public  class  CollectionWrapper<T> {
	private ArrayList<T> mArray;	
	
	public Collection<T> getCollection()
	{
		if(mArray == null)
		{
			mArray = new ArrayList<T>();
		}
		
		return mArray;
	}
}
