package com.epam.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class LengthRuleTest {

	LengthRule rule;
	
	@Before
	public void setup() {
		rule = new LengthRule();
		ReflectionTestUtils.setField(rule, "minLength", 5);
		ReflectionTestUtils.setField(rule, "maxLength", 10);
	}
	
	@Test
	public void testForValidPwdWhenPwdLengthIsInLimit() {
		Assert.assertTrue("given 'password' should satisfy password length rule but it is not.", rule.validate("password"));
	}
	
	@Test
	public void testForValidPwdWhenPwdLengthIsInLimitWithSpecialCharsAndNumbers() {
		Assert.assertTrue("given 'password' should satisfy password length rule but it is not.", rule.validate("pswd11@"));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdIsEmpty() {
		Assert.assertFalse("given 'password' is not satisfying this password length rule.", rule.validate(""));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdIsNull() {
		Assert.assertFalse("given 'password' is not satisfying this password length rule.", rule.validate(null));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdIsLessLength() {
		Assert.assertFalse("given 'password' is not satisfying this password length rule.", rule.validate("1234"));
	}
	
	@Test
	public void testForInvalidPwdWhenPwdIsMoreLength() {
		Assert.assertFalse("given 'password' is not satisfying this password length rule.", rule.validate("123456789123456789"));
	}
	
	@Test
	public void testForValidPwdLengthIsEqualsToMinLength() {
		Assert.assertTrue("given 'password' is not satisfying this password length rule.", rule.validate("12345"));
	}
	
	@Test
	public void testForValidPwdLengthIsEqualsToMaxLength() {
		Assert.assertTrue("given 'password' is not satisfying this password length rule.", rule.validate("1234567890"));
	}
	
	@After
	public void cleanup() {
		rule = null;
	}
}
