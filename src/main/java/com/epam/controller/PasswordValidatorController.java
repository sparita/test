package com.epam.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.Credentials;
import com.epam.dto.PasswordValidatorResponse;
import com.epam.service.PasswordValidatorService;

/**
 * Password validation service controller to expose the service as
 * restful service.
 * 
 * @author suresh
 *
 */
@RestController
public class PasswordValidatorController {

	private static final Log LOGGER = LogFactory.getLog(PasswordValidatorController.class);
	
	@Autowired
	@Qualifier("passwordValidatorService")
	private PasswordValidatorService pwdValidatorService;
	
	@PostMapping("/password/validate")
	public ResponseEntity<PasswordValidatorResponse> validatePassword(@RequestBody Credentials credentials) {
		LOGGER.debug("Controller called...");
		if (credentials == null) {
			 return new ResponseEntity<>(new PasswordValidatorResponse(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pwdValidatorService.validate(credentials.getPassword()), HttpStatus.OK);
	}
}
