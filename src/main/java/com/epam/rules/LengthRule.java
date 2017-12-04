package com.epam.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  Validates the below rule.
 *  
 *  Must be between 5 and 12 characters in length.
 *  
 *  Null password will be treated as empty.
 *  
 * @author suresh
 *
 */
@Component
@ConfigurationProperties
public class LengthRule implements Rule {
	
	private static final Log LOGGER = LogFactory.getLog(LengthRule.class);
	
	private static final String ERROR_MESSAGE = "Password must be between 5 and 12";
	
	@Value("${minLength}")
	private int minLength;
	@Value("${maxLength}")
	private int maxLength;
	
	@Override
	public boolean validate(final String password) {
		LOGGER.trace("LengthRule executed");
		
		if (minLength > maxLength) {
			throw new IllegalArgumentException("Configured min length is greater than max length");
		}
		
		if (minLength < 0 ||  maxLength < 0) {
			throw new IllegalArgumentException("Configured min length/max length can not be a negative numnber");
		}
		
		int length = 0;
		if (password != null) {
			length = password.length();
		}
		
		return length >= minLength && length <= maxLength;	
	}
	
	@Override
	public String getErrorMessage() {
		return ERROR_MESSAGE;
	}
	
	@Override
	public String toString() {
		return "LengthRule [minLength=" + minLength + ", maxLength=" + maxLength + "]";
	}
	
}
