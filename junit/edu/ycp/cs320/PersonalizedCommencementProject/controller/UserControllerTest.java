package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

// JUnits for UserController
public class UserControllerTest {
	private UserController controller;
	private User model;

	@Before
	public void setUp() {
		controller = new UserController();
		model = new User();
		controller.setModel(model);
	}
	
	@Test
	public void testGetAndSetModel() {
		assertEquals(model, controller.getModel());
		User model2 = new User();
		controller.setModel(model2);
		assertEquals(model2, controller.getModel());
	}
	
	@Test
	public void testSetUsername() {
		controller.setUsername("test");
		assertEquals(model.getUsername(), "test");
		controller.setUsername("test2");
		assertEquals(model.getUsername(), "test2");
	}

	@Test
	public void testSetPassword() {
		controller.setPassword("test");
		assertEquals(model.getPassword(), "test");
		controller.setPassword("test2");
		assertEquals(model.getPassword(), "test2");
	}
	
	@Test
	public void testSetType() {
		controller.setType("test");
		assertEquals(model.getType(), "test");
		controller.setType("test2");
		assertEquals(model.getType(), "test2");
	}
	
	@Test
	public void testSetFirstName() {
		controller.setFirstName("test");
		assertEquals(model.getFirstName(), "test");
		controller.setFirstName("test2");
		assertEquals(model.getFirstName(), "test2");
	}
	
	@Test
	public void testSetLastName() {
		controller.setLastName("test");
		assertEquals(model.getLastName(), "test");
		controller.setLastName("test2");
		assertEquals(model.getLastName(), "test2");
	}
}
