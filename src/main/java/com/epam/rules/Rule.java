package com.epam.rules;

/**
 * Interface to specify a Rule that validates the password.
 * @author suresh
 *
 */
public interface Rule {
	
	/**
	 * returns error message.
	 * @return error message.
	 */
	public String getErrorMessage();

	/**
	 * validates the password using the password rule.
	 * @param password plain text password.
	 * @return false if the password is not according to the password rule.
	 */
	public boolean validate(String password);

}
