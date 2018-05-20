package com.sp.friend.model;

public class ErrorResponse {
	public ErrorResponse(String error) {
		super();
		this.error = error;
	}

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
