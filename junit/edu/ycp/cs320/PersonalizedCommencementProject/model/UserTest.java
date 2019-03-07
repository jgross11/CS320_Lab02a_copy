package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User model1, model2, model3;

	@Before
	public void setUp() {
		model1 = new User();
		model2 = new User("testUser", "testPass");
		model3 = new User("testUser", "testPass", "testType");
	}

	@Test
	public void testGetUsername() {
		assertTrue(model2.getUsername().equals("testUser"));
		assertTrue(model3.getUsername().equals("testUser"));
	}
	
	@Test
	public void testSetUsername() {
		model1.setUsername("test");
		model2.setUsername("test");
		model3.setUsername("test");
		assertTrue(model1.getUsername().equals("test"));
		assertTrue(model2.getUsername().equals("test"));
		assertTrue(model3.getUsername().equals("test"));
	}

	@Test
	public void testGetPassword() {
		assertTrue(model2.getPassword().equals("testPass"));
		assertTrue(model3.getPassword().equals("testPass"));
	}
	
	@Test
	public void testSetPassword() {
		model1.setPassword("test");
		model2.setPassword("test");
		model3.setPassword("test");
		assertTrue(model1.getPassword().equals("test"));
		assertTrue(model2.getPassword().equals("test"));
		assertTrue(model3.getPassword().equals("test"));
	}
	
	@Test
	public void testGetType() {
		assertTrue(model3.getType().equals("testType"));
	}
	
	@Test
	public void testSetType() {
		model1.setType("test");
		model2.setType("test");
		model3.setType("test");
		assertTrue(model1.getType().equals("test"));
		assertTrue(model2.getType().equals("test"));
		assertTrue(model3.getType().equals("test"));
	}
	
	@Test
	public void testGetAndSetFirstName() {
		model1.setFirstName("first1");
		model2.setFirstName("first2");
		model3.setFirstName("first3");
		assertTrue(model1.getFirstName().equals("first1"));
		assertTrue(model2.getFirstName().equals("first2"));
		assertTrue(model3.getFirstName().equals("first3"));
	}
	
	@Test
	public void testGetAndSetLastName() {
		model1.setLastName("last1");
		model2.setLastName("last2");
		model3.setLastName("last3");
		assertTrue(model1.getLastName().equals("last1"));
		assertTrue(model2.getLastName().equals("last2"));
		assertTrue(model3.getLastName().equals("last3"));
	}
}
