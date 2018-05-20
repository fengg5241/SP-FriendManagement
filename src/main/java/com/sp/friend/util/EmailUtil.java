package com.sp.friend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.sp.friend.model.SPError;
import com.sp.friend.model.SPException;

public class EmailUtil {
	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	private final static String EMAIL_PATTERN = "^[a-z0-9._-]+@[a-z0-9-]+(?:\\.[a-z0-9-]+)*$";
	
	/**
	 * validate email: not null, max length, pattern
	 * 
	 * @param emails
	 * @throws ApiException
	 */
	public static void validate(String... emails) throws SPException {
		if(emails != null && emails.length > 2) {
			logger.error("There are more than 2 emails to operation together");
			throw new SPException(HttpStatus.BAD_REQUEST, SPError.VALIDATION_ERR);
		}
		
		for (String email : emails) {
			if(StringUtils.isEmpty(email)) {
				logger.error("Email Address is empty");
				throw new SPException(HttpStatus.BAD_REQUEST, SPError.VALIDATION_ERR);
			}
			
			if(email.length() > 255) {
				logger.error("Email Address is more than 255 characters");
				throw new SPException(HttpStatus.BAD_REQUEST, SPError.VALIDATION_ERR);
			}
			
			if(!email.matches(EMAIL_PATTERN)) {
				logger.error("Email Address is not correct format");
				throw new SPException(HttpStatus.BAD_REQUEST, SPError.VALIDATION_ERR);
			}
		}
	}
}
