package com.epam.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold the password validator error messages.
 * 
 * @author suresh
 *
 */
public class PasswordValidatorResponse implements Serializable {
	
	private static final long serialVersionUID = -8216821473822083058L;
	
	private List<String> errorMsgs = new ArrayList<>();
	
	public List<String> getErrorMsgs() {
		List<String> errorMsgsCopy = new ArrayList<>();
		errorMsgsCopy.addAll(errorMsgs);
		return errorMsgsCopy;
	}	
	public boolean isValid() {
		return errorMsgs.isEmpty();
	}
	
	public void addErrorMessage(final String msg) {
		errorMsgs.add(msg);		
	}
	
}