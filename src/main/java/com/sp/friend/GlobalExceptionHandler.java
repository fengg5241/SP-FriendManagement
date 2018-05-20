package com.sp.friend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sp.friend.model.SPException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * handle API exceptions
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = SPException.class)
	public ResponseEntity<Object> handleAppException(SPException ex) {
		SPException.ErrorResponse error = ex.getErrorResponse();
		if(error != null) {
			logger.error("{} : {} ({})", ex.getHttpStatus(), error.getCode(), error.getMessage());
			
		}
		return new ResponseEntity<Object>(error, ex.getHttpStatus());
	}
	
	/**
	 *  handle all uncatched Exception
	 * @param ex
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity handleUncatchException(Exception ex) {
		
		logger.error("Internal Server Exception thrown :", ex);
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
