package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
 * TODO: These tests are incomplete as the Graduate class currently (3/10)
 * TODO: contains only a portion of the attributes/functions in its class diagram
 * TODO: As such, these tests are incomplete and will need to be updated when the 
 * TODO: other classes (admin, advisor, etc.) are implemented. 
 */

// JUnits for Graduate
public class GraduateTest {
	private Graduate model1, model2, model3;

	@Before
	public void setUp() {
		model1 = new Graduate(new User("", "", "", "", ""));
		model2 = new Graduate(new User("testUser", "testPass", "", "", ""));
		model3 = new Graduate(new User("testUser", "testPass", "testType", "", ""));
	}
	
	@Test
	public void testGetAndSetMajor() {
		model1.setMajor("Test Studies");
		model2.setMajor("Testography");
		model3.setMajor("Etestemology");
		
		assertEquals(model1.getMajor(), "Test Studies");
		assertEquals(model2.getMajor(), "Testography");
		assertEquals(model3.getMajor(), "Etestemology");
	} 
	
	@Test
	public void testGetAndSetMinor() {
		model1.setMinor("Test Studies");
		model2.setMinor("Testography");
		model3.setMinor("Etestemology");
		
		assertEquals(model1.getMinor(), "Test Studies");
		assertEquals(model2.getMinor(), "Testography");
		assertEquals(model3.getMinor(), "Etestemology");
	}
	
	@Test
	public void testGetAndQRCodeString() {
		model1.setQRCodeString("model1");
		model2.setQRCodeString("model2");
		model3.setQRCodeString("model3");
		
		assertEquals(model1.getQRCodeString(), "model1");
		assertEquals(model2.getQRCodeString(), "model2");
		assertEquals(model3.getQRCodeString(), "model3");
	}
	
	@Test
	public void testGetAndStatus() {
		model1.setStatus(true);
		model2.setStatus(false);
		model3.setStatus(true);
		
		assertEquals(model1.getStatus(), true);
		assertEquals(model2.getStatus(), false);
		assertEquals(model3.getStatus(), true);
	}
}
