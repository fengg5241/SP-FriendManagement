package com.sp.friend.model;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class SPException extends Exception {

	private HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;

	private ErrorResponse errorResponse;
	
	public SPException(HttpStatus httpStatus, SPError error) {
		super();
		this.httpStatus = httpStatus;
		this.errorResponse = new ErrorResponse(error, error.message());
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public static class ErrorResponse {
		private SPError code;
		private String message;

		public ErrorResponse(SPError code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		public SPError getCode() {
			return code;
		}

		public void setCode(SPError code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
