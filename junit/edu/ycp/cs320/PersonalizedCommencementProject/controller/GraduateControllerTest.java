package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.InfoState;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;

/*
 * TODO: These tests are incomplete as the Graduate class currently (3/10)
 * TODO: contains only a portion of the attributes/functions in its class diagram
 * TODO: As such, these tests are incomplete and will need to be updated when the 
 * TODO: other classes (admin, advisor, etc.) are implemented. 
*/

// JUnits for GraduateController
public class GraduateControllerTest {
	private Graduate model1, model2, model3;
	private GraduateController controller1, controller2, controller3;
	
	@Before
	public void setUp() {
		model1 = new Graduate();
		model2 = new Graduate();
		model3 = new Graduate();
		controller1 = new GraduateController();
		controller2 = new GraduateController();
		controller3 = new GraduateController();
		controller1.setModel(model1);
		controller2.setModel(model2);
		controller3.setModel(model3);
	}
	
	@Test
	public void testGetAndSetModel() {
		assertEquals(controller1.getModel(), model1);
		assertEquals(controller2.getModel(), model2);
		assertEquals(controller3.getModel(), model3);
	}
	
	@Test
	public void testSetMajor() {
		controller1.setMajor("Test Studies");
		controller2.setMajor("Testography");
		controller3.setMajor("Etestomology");
		
		assertEquals(model1.getMajor(), "Test Studies");
		assertEquals(model2.getMajor(), "Testography");
		assertEquals(model3.getMajor(), "Etestomology");
	}
	
	@Test
	public void testSetMinor() {
		controller1.setMinor("Test Studies");
		controller2.setMinor("Testography");
		controller3.setMinor("Etestomology");
		
		assertEquals(model1.getMinor(), "Test Studies");
		assertEquals(model2.getMinor(), "Testography");
		assertEquals(model3.getMinor(), "Etestomology");
	}
	
	@Test
	public void testSetQRCodeString() {
		controller1.setQRCodeString("controller1");
		controller2.setQRCodeString("controller2");
		controller3.setQRCodeString("controller3");
		
		assertEquals(model1.getQRCodeString(), "controller1");
		assertEquals(model2.getQRCodeString(), "controller2");
		assertEquals(model3.getQRCodeString(), "controller3");
	}

	@Test
	public void testSetStatus() {
		controller1.setStatus(false);
		controller2.setStatus(true);
		controller3.setStatus(false);
		
		assertEquals(model1.getStatus(), false);
		assertEquals(model2.getStatus(), true);
		assertEquals(model3.getStatus(), false);
	}
	
	@Test
	public void testGetAdvisor() {
		// Edit when database is finished 
		Advisor advisor = new Advisor();
		//Advisor advisor2 = new Advisor();
		//Advisor advisor3 = new Advisor();
		controller1.setAdvisor(advisor);
		controller2.setAdvisor(advisor);
		controller3.setAdvisor(advisor);
		
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
		controller1.setCurrentInfo(currentInfo);
		controller2.setCurrentInfo(currentInfo);
		controller3.setCurrentInfo(currentInfo);
		
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
		controller1.setPendingInfo(pendingInfo);
		controller2.setPendingInfo(pendingInfo);
		controller3.setPendingInfo(pendingInfo);
		
		assertEquals(model1.getPendingInfo(), pendingInfo);
		assertEquals(model2.getPendingInfo(), pendingInfo);
		assertEquals(model3.getPendingInfo(), pendingInfo); 
	}
}
