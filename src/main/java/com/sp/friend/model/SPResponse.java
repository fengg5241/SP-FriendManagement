package com.sp.friend.model;

public class SPResponse {

	public SPResponse() {
	}
	
	public SPResponse(boolean success) {
		super();
		this.success = success;
	}

	private boolean success = true;

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
