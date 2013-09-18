package com.hibernate.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.Test;

import com.xinyuan.model.User;

public class TestCase {

	private static SessionFactory sessionFactory;

	public static void beforeClass() {
		sessionFactory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
//		ConstraintValidator
//		ConstraintValidatorContext
	}

	@Test
	public void test() {
		// validator
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		User user = new User();
		Set<ConstraintViolation<User>> constraintViolations = validator
				.validate(user);
		assertEquals(1, constraintViolations.size());
		assertEquals("The car has to pass the vehicle inspection first",
				constraintViolations.iterator().next().getMessage());

		// boolean isSuccess = new HibernateDAO().saveObject(user);

	}

	public static void main(String[] args) {
		beforeClass();
		new TestCase().test();
		afterClass();
	}

}
