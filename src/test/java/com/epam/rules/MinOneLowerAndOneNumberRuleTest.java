package com.epam.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinOneLowerAndOneNumberRuleTest {

	MinOneLowerAndOneNumberRule rule;
	
	@Before
	public void setup() {
		rule = new MinOneLowerAndOneNumberRule();	
	}
	
	@Test
	public void testForValidPwdWithOneLowerLetterAndOneNumber() {
		Assert.assertTrue("All lower case letters and numbers are in the password which should pass the test.", rule.validate("valid1ne"));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdIsEmpty() {
		Assert.assertFalse("Empty password given and it should fail.", rule.validate(""));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdIsNull() {
		Assert.assertFalse("Null given as password which should fail the test.", rule.validate(null));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdHasOnlyNumber() {
		Assert.assertFalse("Only numbers are given in password which should fail test.", rule.validate("1234"));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdHasAllLowerCaseLetters() {
		Assert.assertFalse("Only lower case letters in password and no number in it which should fail the test.", rule.validate("asef"));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdHasLowerAndCapitalLetters() {
		Assert.assertFalse("Upper case letter given in password which should fail the test.", rule.validate("Asef1"));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdHasSpecialChars() {
		Assert.assertFalse("Special characters in the passwould which should fail the test.", rule.validate("As$%ef1"));
	}
	
	@After
	public void cleanup() {
		rule = null;
	}
}
