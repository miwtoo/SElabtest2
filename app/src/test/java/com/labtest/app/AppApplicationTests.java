package com.labtest.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.*;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class AppApplicationTests {

	//@Autowired
	//private StudentRepository studentRepository;

	@Autowired
	private TestEntityManager entityManager;

	//private Validator validator;


	//@Before
	//public void setup(){
		//ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		//validator = factory.getValidator();
	//}

	@Test
	public void testStudentFirstNameCannotBeNull() {
		Student s = new Student();
		//s.setId(1L);
		s.setName("Ok");

		s.setTeacher(null);

		try {
			entityManager.persist(s);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

}

