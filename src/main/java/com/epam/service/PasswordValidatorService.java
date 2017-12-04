package com.epam.service;

import com.epam.dto.PasswordValidatorResponse;

/**
 * Password validate service to test against the given validations rules which can be added or deleted.
 * 
 * Response will contain the list of error messages if validation errors exist.
 * 
 * @author suresh
 *
 */

public interface PasswordValidatorService {

	/**
	 * Validate method to validate the given password.
	 * 
	 * @param password
	 *            which contains password in plain text
	 * @return the response with list of strings which are error messages in case of
	 *         validation errors or empty list of strings.
	 */
	public PasswordValidatorResponse validate(final String password);
}
