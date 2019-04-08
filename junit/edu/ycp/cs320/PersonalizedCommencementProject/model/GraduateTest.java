package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.InfoState;

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
	
	@Test
	public void testGetAdvisor() {
		// Edit when database is finished 
		Advisor advisor = new Advisor();
		//Advisor advisor2 = new Advisor();
		//Advisor advisor3 = new Advisor();
		model1.setAdvisor(advisor);
		model2.setAdvisor(advisor);
		model3.setAdvisor(advisor);
		
		assertEquals(model1.getAdvisor(), advisor);
		assertEquals(model2.getAdvisor(), advisor);
		assertEquals(model3.getAdvisor(), advisor); 
	}
	
	@Test
	public void testGetCurrentInfo() {
		// Edit when database is finished 
		InfoState currentInfo = new InfoState();
		//InfoState currentInfo2 = new InfoState();
		//InfoState currentInfo3 = new InfoState();
		model1.setCurrentInfo(currentInfo);
		model2.setCurrentInfo(currentInfo);
		model3.setCurrentInfo(currentInfo);
		
		assertEquals(model1.getCurrentInfo(), currentInfo);
		assertEquals(model2.getCurrentInfo(), currentInfo);
		assertEquals(model3.getCurrentInfo(), currentInfo); 
	}
	
	@Test
	public void testGetPendingInfo() {
		// Edit when database is finished 
		InfoState pendingInfo = new InfoState();
		//InfoState pendingInfo2 = new InfoState();
		//InfoState pendingInfo3 = new InfoState();
		model1.setPendingInfo(pendingInfo);
		model2.setPendingInfo(pendingInfo);
		model3.setPendingInfo(pendingInfo);
		
		assertEquals(model1.getPendingInfo(), pendingInfo);
		assertEquals(model2.getPendingInfo(), pendingInfo);
		assertEquals(model3.getPendingInfo(), pendingInfo); 
	}
}
