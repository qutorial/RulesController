package com.mbeddr.ext.utils.tools;

public class Pair<T1, T2> {
	private T1 mLeft;
	private T2 mRight;

	public Pair(T1 left, T2 right) {
		mLeft = left;
		mRight = right; 
	}

	public T1 getLeft() {
		return mLeft;
	}
	
	public T2 getRight() {
		return mRight;
	}
	
}
