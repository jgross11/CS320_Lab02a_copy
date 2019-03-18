package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.model.Advisor;
import edu.ycp.cs320.PersonalizedCommencementProject.model.Graduate;

// JUnits for Advisor Controller
public class AdvisorControllerTest {
	private Advisor model1, model2, model3;
	private AdvisorController controller1, controller2, controller3;

	@Before
	public void setUp() {
		
		model1 = new Advisor();
		model2 = new Advisor();
		model3 = new Advisor();
		
		controller1 = new AdvisorController();
		controller2 = new AdvisorController();
		controller3 = new AdvisorController();
		
		controller1.setModel(model1);
		controller2.setModel(model2);
		controller3.setModel(model3);

	}
	
	@Test
	public void testGetModel() {
		assertTrue(controller1.getModel().equals(model1));
		assertTrue(controller2.getModel().equals(model2));
		assertTrue(controller3.getModel().equals(model3));
	}
	
	@Test
	public void testSetNumGraduates() {
		controller1.setNumGraduates(1);
		controller2.setNumGraduates(13);
		controller3.setNumGraduates(102);
		
		assertEquals(model1.getNumGraduates(), 1);
		assertEquals(model2.getNumGraduates(), 13);
		assertEquals(model3.getNumGraduates(), 102);
	} 
	
	@Test
	public void testSetNumCompletedGraduates() {
		controller1.setNumCompletedGraduates(0);
		controller2.setNumCompletedGraduates(3);
		controller3.setNumCompletedGraduates(10);
		
		assertEquals(model1.getNumCompletedGraduates(), 0);
		assertEquals(model2.getNumCompletedGraduates(), 3);
		assertEquals(model3.getNumCompletedGraduates(), 10);
	} 
	
	@Test
	public void testSetGraduates() {
		Graduate[] grad1 = new Graduate[3];
		Graduate[] grad2 = new Graduate[13];
		Graduate[] grad3 = new Graduate[131];
		
		controller1.setGraduates(grad1);
		controller2.setGraduates(grad2);
		controller3.setGraduates(grad3);
		
		assertTrue(model1.getGraduates().equals(grad1));
		assertTrue(model2.getGraduates().equals(grad2));
		assertTrue(model3.getGraduates().equals(grad3));
	}
	
	@Test 
	public void testSetAcademicInformation() {
		controller1.setAcademicInformation("Testology");
		controller2.setAcademicInformation("Test Studies");
		controller3.setAcademicInformation("Etestimology");
		
		assertEquals(model1.getAcademicInformation(), "Testology");
		assertEquals(model2.getAcademicInformation(), "Test Studies");
		assertEquals(model3.getAcademicInformation(), "Etestimology");
	}
	
	@Test 
	public void testSetStatus() {
		controller1.setStatus(true);
		controller2.setStatus(false);
		
		assertTrue(model1.getStatus());
		assertTrue(!model2.getStatus());
	}
	
	@Test
	public void testCalculateNumCompletedGraduates() {
		
		// Create Graduate arrays
		Graduate[] grad1 = new Graduate[10];
		Graduate[] grad2 = new Graduate[10];
		Graduate[] grad3 = new Graduate[10];
		
		// Populate arrays
		for(int i = 0; i < 10; i++) {
			grad1[i] = new Graduate();
			grad2[i] = new Graduate();
			grad3[i] = new Graduate();
		}
		
		// Set every Graduate status to true
		for(Graduate grad : grad1) {
			grad.setStatus(true);
		}
		
		// Set every Graduate status to false
		for(Graduate grad : grad2) {
			grad.setStatus(false);
		}
		
		// Set the status of even-indexed Graduates to true, odd-... to false
		boolean result;
		int counter = 0;
		for(Graduate grad : grad3) {
			result = (counter % 2 == 0) ? true : false;
			grad.setStatus(result);
			counter++;
		}
		
		// Assign Graduate array to model
		model1.setGraduates(grad1);
		model2.setGraduates(grad2);
		model3.setGraduates(grad3);
		
		// Call to calculate number of completed graduates
		controller1.calculateNumCompletedGraduates();
		controller2.calculateNumCompletedGraduates();
		controller3.calculateNumCompletedGraduates();
		
		assertEquals(model1.getNumCompletedGraduates(), 10);
		assertEquals(model2.getNumCompletedGraduates(), 0);
		assertEquals(model3.getNumCompletedGraduates(), 5);
	}
	
	@Test
	public void testeCalculateStatus() {
		
		// Create Graduate arrays
		Graduate[] grad1 = new Graduate[10];
		Graduate[] grad2 = new Graduate[10];
		Graduate[] grad3 = new Graduate[10];
		
		// Populate arrays
		for(int i = 0; i < 10; i++) {
			grad1[i] = new Graduate();
			grad2[i] = new Graduate();
			grad3[i] = new Graduate();
		}
		
		// Set every Graduate status to true
		for(Graduate grad : grad1) {
			grad.setStatus(true);
		}
		
		// Set every Graduate status to false
		for(Graduate grad : grad2) {
			grad.setStatus(false);
		}
		
		// Set the status of even-indexed Graduates to true, odd-... to false
		boolean result;
		int counter = 0;
		for(Graduate grad : grad3) {
			result = (counter % 2 == 0) ? true : false;
			grad.setStatus(result);
			counter++;
		}
		
		// Assign Graduate array to model
		model1.setGraduates(grad1);
		model2.setGraduates(grad2);
		model3.setGraduates(grad3);
		
		// Call to calculate statuses
		controller1.calculateStatus();
		controller2.calculateStatus();
		controller3.calculateStatus();
		
		assertTrue(model1.getStatus());
		assertTrue(!model2.getStatus());
		assertTrue(!model3.getStatus());
	}
	
}
