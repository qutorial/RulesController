package org.fortiss.smg.rulescontroller.expressions.types;

public class TypeBase {

	@Override
	public boolean equals(Object obj) {
		return this.getClass() == obj.getClass();
	}

}
