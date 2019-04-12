package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Admin;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.User;

/*
 * TODO: These tests are incomplete as the Graduate class currently (3/10)
 * TODO: contains only a portion of the attributes/functions in its class diagram
 * TODO: As such, these tests are incomplete and will need to be updated when the 
 * TODO: other classes (admin, advisor, etc.) are implemented. 
 */

// JUnits for Graduate
public class AdminControllerTest {
	private Admin model1, model2, model3;
	private AdminController controller1, controller2, controller3; 
	
	@Before
	public void setUp() {
		controller1 = new AdminController(); 
		controller2 = new AdminController(); 
		controller3 = new AdminController();
		model1 = new Admin(new User("Dchism" ,"Password","TestType","Dennis","Chism"));
		model2 = new Admin(new User("Jgross" ,"Password","TestType","Josh","Gross"));
		model3 = new Admin(new User("Bill" ,"Password","TestType","will","bill"));
		
		controller1.setModel(model1);
		controller2.setModel(model2);
		controller3.setModel(model3);
	}
	
	@Test
	public void TestSetandGetDate() {
		long d1 = 200;
		long d2 = 20000; 
		long d3 = 20000000; 
		
		controller1.setDate(d1);
		controller2.setDate(d2); 
		controller3.setDate(d3);
		
		assertEquals(model1.getDate().getTime(), d1); 
		assertEquals(model2.getDate().getTime(), d2); 
		assertEquals(model3.getDate().getTime(), d3); 	
	}
}
