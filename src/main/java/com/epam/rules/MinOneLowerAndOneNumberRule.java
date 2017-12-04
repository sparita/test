package com.epam.rules;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 *  Validates the below rule.
 *  
 *  Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
 *  
 *  Null password will be treated as empty.
 *  
 * @author suresh
 *
 */
@Component
public class MinOneLowerAndOneNumberRule implements Rule {
	
	private static final Log LOGGER = LogFactory.getLog(MinOneLowerAndOneNumberRule.class);
	private static final String ONE_LOWERCASELETTER_PATTERN_REGX = "((?:[a-z][a-z]*[0-9]+[a-z0-9]*))";
	
	private static final Pattern ONE_LOWER_CASE_LETTER_PATTERN = Pattern.compile(ONE_LOWERCASELETTER_PATTERN_REGX);
	
	private static final String ERROR_MESSAGE = "Password must consist of a mixture of lowercase letters and numerical digits only, with at least one of each";

	@Override
	public boolean validate(final String password) {
		LOGGER.trace("MinOneLowerAndOneNumberRule executed");
		if (password == null) {
			return ONE_LOWER_CASE_LETTER_PATTERN.matcher("").matches();
		}
		return ONE_LOWER_CASE_LETTER_PATTERN.matcher(password).matches();
	}
	
	@Override
	public String getErrorMessage() {
		return ERROR_MESSAGE;
	}

}
