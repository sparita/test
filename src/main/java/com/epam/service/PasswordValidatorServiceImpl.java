package com.epam.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.epam.dto.PasswordValidatorResponse;

/**
 * Password validate service to test against the given validations rules which can be added or deleted.
 * 
 * Response will contain the list of error messages if validation errors exist.
 * 
 * @author suresh
 *
 */

@Service("passwordValidatorService")
public class PasswordValidatorServiceImpl implements PasswordValidatorService {

	private static final Log LOGGER = LogFactory.getLog(PasswordValidatorServiceImpl.class);
	
	@Autowired
	private List<com.epam.rules.Rule> rules;
	
	/**
	 * Validate method to validate the given password.
	 * 
	 * @param password
	 *            which contains password in plain text.
	 * @return the response with list of strings which are error messages in case of
	 *         validation errors or empty list of strings.
	 */
	public PasswordValidatorResponse validate(final String password) {
		LOGGER.trace("validate service is called.");
		final PasswordValidatorResponse response = new PasswordValidatorResponse();
		if (password != null && !StringUtils.isEmpty(password)) {
			LOGGER.debug("credentials are avaialabble..");
			rules.forEach(rule -> {
				if (!rule.validate(password)) {
					response.addErrorMessage(rule.getErrorMessage());
				}
			});
		} else {
			LOGGER.debug("credentials are missing..");
			response.addErrorMessage("No valid credentials are provided.");
		}
		LOGGER.trace("validate service is completed.");
		return response;
	}

}
