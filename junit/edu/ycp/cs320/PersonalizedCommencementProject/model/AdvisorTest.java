package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// JUnits for Advisor
public class AdvisorTest {
	private Advisor model1, model2, model3;

	@Before
	public void setUp() {
		model1 = new Advisor(new User("", "", "", "", ""));
		model2 = new Advisor(new User("testUser", "testPass", "", "", ""));
		model3 = new Advisor(new User("testUser", "testPass", "testType", "", ""));
	}
	
	@Test
	public void testGetAndSetNumGraduates() {
		model1.setNumGraduates(1);
		model2.setNumGraduates(13);
		model3.setNumGraduates(102);
		
		assertEquals(model1.getNumGraduates(), 1);
		assertEquals(model2.getNumGraduates(), 13);
		assertEquals(model3.getNumGraduates(), 102);
	} 

	@Test
	public void testGetAndSetNumCompletedGraduates() {
		model1.setNumCompletedGraduates(1);
		model2.setNumCompletedGraduates(13);
		model3.setNumCompletedGraduates(102);
		
		assertEquals(model1.getNumCompletedGraduates(), 1);
		assertEquals(model2.getNumCompletedGraduates(), 13);
		assertEquals(model3.getNumCompletedGraduates(), 102);
	}
	
	@Test
	public void testGetAndSetGraduates() {
		Graduate[] grad1 = new Graduate[3];
		Graduate[] grad2 = new Graduate[31];
		Graduate[] grad3 = new Graduate[0];
		
		model1.setGraduates(grad1);
		model2.setGraduates(grad2);
		model3.setGraduates(grad3);
		
		assertTrue(model1.getGraduates().equals(grad1));
		assertTrue(model2.getGraduates().equals(grad2));
		assertTrue(model3.getGraduates().equals(grad3));
	}
	
	@Test
	public void testAddAndGetGraduate() {
		Graduate grad1 = new Graduate();
		Graduate grad2 = new Graduate();
		Graduate grad3 = new Graduate();
		
		model1.setGraduates(new Graduate[3]);
		model2.setGraduates(new Graduate[31]);
		model3.setGraduates(new Graduate[0]);
		
		model1.addGraduate(0, grad1);
		model2.addGraduate(13, grad2);
		model3.addGraduate(0, grad3);
		
		assertTrue(model1.getGraduate(0).equals(grad1));
		assertTrue(model2.getGraduate(13).equals(grad2));
		
		// an exception should be thrown here due to array size being
		// less than the desired index - check that exception is thrown
		boolean exceptionThrown = false;
		try {
			model3.getGraduate(0);
		}
		catch(IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void testGetAndSetAcademicInformation() {
		model1.setAcademicInformation("Testology");
		model2.setAcademicInformation("Test Studies");
		model3.setAcademicInformation("Etestimology");
		
		assertEquals(model1.getAcademicInformation(), "Testology");
		assertEquals(model2.getAcademicInformation(), "Test Studies");
		assertEquals(model3.getAcademicInformation(), "Etestimology");
	}
	
	@Test
	public void testGetAndSetStatus() {
		model1.setStatus(true);
		model2.setStatus(false);
		
		assertTrue(model1.getStatus());
		assertTrue(!model2.getStatus());
	}
	
	@Test
	public void testCalculateStatus() {
		// Create new Graduate arrays
		Graduate[] grad1 = new Graduate[10];
		Graduate[] grad2 = new Graduate[10];
		Graduate[] grad3 = new Graduate[10];
		
		// Populate arrays
		for(int i = 0; i < 10; i++) {
			grad1[i] = new Graduate();
			grad2[i] = new Graduate();
			grad3[i] = new Graduate();
		}
		
		// Set the status of every Graduate to true
		for(Graduate grad : grad1) {
			grad.setStatus(true);
		}
		
		// Set the status of every Graduate to false
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
		
		// assign Advisor Graduate arrays
		model1.setGraduates(grad1);
		model2.setGraduates(grad2);
		model3.setGraduates(grad3);
		
		// call to calculate status
		model1.calculateStatus();
		model2.calculateStatus();
		model3.calculateStatus();
		
		assertTrue(model1.getStatus());
		assertTrue(!model2.getStatus());
		assertTrue(!model3.getStatus());
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
		
		// assign Advisor Graduate arrays
		model1.setGraduates(grad1);
		model2.setGraduates(grad2);
		model3.setGraduates(grad3);
		
		// call to calculate number of completed graduates
		model1.calculateNumCompletedGraduates();
		model2.calculateNumCompletedGraduates();
		model3.calculateNumCompletedGraduates();
		
		assertEquals(model1.getNumCompletedGraduates(), 10);
		assertEquals(model2.getNumCompletedGraduates(), 0);
		assertEquals(model3.getNumCompletedGraduates(), 5);
	}
}
