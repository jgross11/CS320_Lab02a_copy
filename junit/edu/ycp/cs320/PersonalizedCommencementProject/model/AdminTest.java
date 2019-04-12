package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;


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
	public void TestSetandGetDate() {
		long d1 = 200;
		long d2 = 20000; 
		long d3 = 20000000; 
		
		model1.setDate(d1);
		model2.setDate(d2);
		model3.setDate(d3);
		
		assertEquals(model1.getDate().getTime(),d1); 
		assertEquals(model2.getDate().getTime(),d2); 
		assertEquals(model3.getDate().getTime(),d3); 	
	}
}
