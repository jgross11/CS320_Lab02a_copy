package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/*
 * TODO: These tests are incomplete as the Graduate class currently (3/10)
 * TODO: contains only a portion of the attributes/functions in its class diagram
 * TODO: As such, these tests are incomplete and will need to be updated when the 
 * TODO: other classes (admin, advisor, etc.) are implemented. 
 */

// JUnits for Graduate
public class AdminTest {
	private Admin model1, model2, model3;
	
	@Before
	public void setUp() {
		model1 = new Admin(new User("Dchism" ,"Password","TestType","Dennis","Chism"));
		model2 = new Admin(new User("Jgross" ,"Password","TestType","Josh","Gross"));
		model3 = new Admin(new User("Bill" ,"Password","TestType","will","bill"));
	}
	
	@Test
	public void TestSetandGetNumOfcompletedAdvisors() {
		model1.setNumOfCompletedAdvisors(10);
		model2.setNumOfCompletedAdvisors(54);
		model3.setNumOfCompletedAdvisors(108);
		
		assertEquals(model1.getNumOfCompletedAdvisors(),10); 
		assertEquals(model2.getNumOfCompletedAdvisors(),54); 
		assertEquals(model3.getNumOfCompletedAdvisors(),108); 
		
	}
	
	@Test
	public void TestSetandGetNumberOfAdvisors() {
		model1.setNumAdvisors(10);
		model2.setNumAdvisors(54);
		model3.setNumAdvisors(108);
		
		assertEquals(model1.getNumAdvisors(),10); 
		assertEquals(model2.getNumAdvisors(),54); 
		assertEquals(model3.getNumAdvisors(),108);
		
	}
	
	@Test
	public void TestSetandGetAdvisorsandAddAdvisors() {
		Advisor[] Advisor1 = new Advisor[3];
		Advisor[] Advisor2 = new Advisor[31];
		Advisor[] Advisor3 = new Advisor[22];
		
		model1.setAdvisors(Advisor1);
		model2.setAdvisors(Advisor2);
		model3.setAdvisors(Advisor3);
		
		assertTrue(model1.getAdvisors().equals(Advisor1)); 
		assertTrue(model2.getAdvisors().equals(Advisor2)); 
		assertTrue(model3.getAdvisors().equals(Advisor3)); 
		
	}
	
	@Test
	public void TestSetandGetDate() {
		Date d1 = new Date(2002,1,3);
		Date d2 = new Date(2009,5,25); 
		Date d3 = new Date(2011,6,29); 
		
		model1.setDate(2002, 1, 3);
		model2.setDate(2009, 5, 25);
		model3.setDate(2011, 6, 29);
		
		assertEquals(model1.getDate(),d1); 
		assertEquals(model2.getDate(),d2); 
		assertEquals(model3.getDate(),d3); 	
	}
	
	//@Test
	//public void TestAddAdvisorandGetCertainAdvaisor() {
		//Advisor Dave = new Advisor(); 
		//Advisor Willma = new Advisor(); 
		//Advisor Jess = new Advisor(); 
		
		//model1.addAdvisor(0, Dave);
		//model2.addAdvisor(0, Willma);
		//model3.addAdvisor(0, Jess);
		
		//assertTrue(model1.getCertainAdvisor(0).equals(Dave)); 
		//assertTrue(model2.getCertainAdvisor(0).equals(Willma)); 
		//assertTrue(model3.getCertainAdvisor(0).equals(Jess)); 
	//}
}
