package com.epam.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoSequencialTextRuleTest {

	NoSequencialTextRule rule;
	
	@Before
	public void setup() {
		rule = new NoSequencialTextRule();	
	}
	
	@Test
	public void testForValidPwdWithNullPwd() {
		Assert.assertTrue("Null password should pass this test.", rule.validate(null));
	}
	
	@Test
	public void testForValidPwdWithEmptyPwd() {
		Assert.assertTrue("Empty password should pass this test.", rule.validate(""));
	}
	
	@Test
	public void testForValidPwdWithNoSequentialPwdText() {
		Assert.assertTrue("No sequencial words in password should pass this test.", rule.validate("password1"));
	}
	
	@Test
	public void testForInvalidPwdWithSequentialPwdText() {
		Assert.assertFalse("Sequencial words in password should pass this test.", rule.validate("failfail"));
	}
	
	@Test
	public void testForValidPwdWithSequentialTextAroundAnotherWord() {
		Assert.assertTrue("Sequencial words in password should pass this test.", rule.validate("passtopass"));
	}
	
	@After
	public void cleanup() {
		rule = null;
	}
}
