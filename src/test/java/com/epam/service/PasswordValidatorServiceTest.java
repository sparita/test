package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.epam.dto.PasswordValidatorResponse;
import com.epam.rules.LengthRule;
import com.epam.rules.MinOneLowerAndOneNumberRule;
import com.epam.rules.NoSequencialTextRule;
import com.epam.rules.Rule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class PasswordValidatorServiceTest {

	@Configuration
	static class AccountServiceTestContextConfiguration {
		
		@Bean
		public PasswordValidatorService passwordValidatorService() {
			return new PasswordValidatorServiceImpl();
		}
		
		@Bean
		public List<com.epam.rules.Rule> rules() {
			LengthRule lengthRule = new LengthRule();		
			ReflectionTestUtils.setField(lengthRule, "minLength", 5);
			ReflectionTestUtils.setField(lengthRule, "maxLength", 10);
			MinOneLowerAndOneNumberRule minOneLowerAndOneNumberRule = new MinOneLowerAndOneNumberRule();		
			NoSequencialTextRule noSequencialTextRule = new NoSequencialTextRule();
			List<Rule> rules = new ArrayList<>();
			rules.add(lengthRule);
			rules.add(minOneLowerAndOneNumberRule);
			rules.add(noSequencialTextRule);
			return rules;
		}
	}
	
	@Autowired
	private PasswordValidatorService passwordValidatorService;
	
	@Test
	public void testValidateWithEmtpyCredsForFalse() {
		PasswordValidatorResponse response = passwordValidatorService.validate(null);
		Assert.assertFalse("When no passwrod is provided, error message is expected but no message returned.", response.getErrorMsgs().isEmpty());
	}
	
	@Test
	public void testValidateWithInvalidCredsForFalse() {
		PasswordValidatorResponse response = passwordValidatorService.validate("invalid");
		Assert.assertFalse("When invalid passwrod is provided, error message is expected but no message returned.", response.getErrorMsgs().isEmpty());
	}
	
	@Test
	public void testValidateWithValidCredsForTrue() {
		PasswordValidatorResponse response = passwordValidatorService.validate("valid1ne");
		Assert.assertTrue("When valid passwrod is provided, error message is expected but message returned.", response.getErrorMsgs().isEmpty());
	}
}
