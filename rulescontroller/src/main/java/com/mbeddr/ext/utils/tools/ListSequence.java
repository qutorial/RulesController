package com.mbeddr.ext.utils.tools;

import java.util.List;

public class ListSequence<T> {

	List<T> mList;
	
	private ListSequence()
	{		
	}
	
	public static <T> ListSequence<T> fromList(List<T> list) {
		ListSequence<T> s = new ListSequence<T>();
		s.mList = list;
		return s;
	}

	public T foldLeft(T first,
			ILeftCombinator<T> iLeftCombinator) {
		T res = first;
		for(T it : mList)
		{
			res = iLeftCombinator.combine(res, it);
		}
		
		return res;
	}

}
