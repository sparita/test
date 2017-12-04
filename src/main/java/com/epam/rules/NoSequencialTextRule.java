package com.epam.rules;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Validates the below rule.
 * 
 * Must not contain any sequence of characters immediately followed by the same
 * sequence.
 * 
 * Null password will be treated as empty.
 * @author suresh
 *
 */
@Component
public class NoSequencialTextRule implements Rule {

	private static final Log LOGGER = LogFactory.getLog(NoSequencialTextRule.class);

	private static final String ERROR_MESSAGE = "Must not contain any sequence of characters immediately followed by the same sequence";
	private static final String NO_SEQUENTIAL_PATTERN_REGX = "(\\w{2,})\\1";

	public static final Pattern NO_SEQUENTIAL_PATTERN = Pattern.compile(NO_SEQUENTIAL_PATTERN_REGX);

	@Override
	public boolean validate(final String password) {
		LOGGER.trace("NoSequencialTextRule executed");
		if (password == null) {
			return !NO_SEQUENTIAL_PATTERN.matcher("").matches();
		}
		return !NO_SEQUENTIAL_PATTERN.matcher(password).matches();
	}

	@Override
	public String getErrorMessage() {
		return ERROR_MESSAGE;
	}

}
