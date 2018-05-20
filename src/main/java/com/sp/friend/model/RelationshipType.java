package com.sp.friend.model;

public enum RelationshipType {
	// BINARY SUBSCRIBE:01,BLOCK:10
	NONE(0),SUBSCRIBE(1),BLOCK(2);
	
	private int value = 0;
	
	private RelationshipType(int value) {
        this.setValue(value);
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
