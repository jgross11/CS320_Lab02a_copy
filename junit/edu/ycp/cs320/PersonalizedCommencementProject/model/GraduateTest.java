package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.ContentComponent;
import edu.ycp.cs320.PersonalizedCommencementProject.miscellaneousClasses.InfoState;


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
	public void testGetAndSetQRCodeString() {
		model1.setQRCodeString("model1");
		model2.setQRCodeString("model2");
		model3.setQRCodeString("model3");
		
		assertEquals(model1.getQRCodeString(), "model1");
		assertEquals(model2.getQRCodeString(), "model2");
		assertEquals(model3.getQRCodeString(), "model3");
	}
	
	@Test
	public void testGetAndSetStatus() {
		model1.setStatus(true);
		model2.setStatus(false);
		model3.setStatus(true);
		
		assertEquals(model1.getStatus(), true);
		assertEquals(model2.getStatus(), false);
		assertEquals(model3.getStatus(), true);
	}
	
	@Test
	public void testGetAndSetAdvisor() {
		// Edit when database is finished 
		Advisor advisor = new Advisor();
		Advisor advisor2 = new Advisor();
		Advisor advisor3 = new Advisor();
		model1.setAdvisor(advisor);
		model2.setAdvisor(advisor2);
		model3.setAdvisor(advisor3);
		
		assertEquals(model1.getAdvisor(), advisor);
		assertEquals(model2.getAdvisor(), advisor2);
		assertEquals(model3.getAdvisor(), advisor3); 
	}
	
	@Test
	public void testGetAndSetCurrentInfo() {
		// Edit when database is finished 
		InfoState currentInfo = new InfoState();
		InfoState currentInfo2 = new InfoState();
		InfoState currentInfo3 = new InfoState();
		model1.setCurrentInfo(currentInfo);
		model2.setCurrentInfo(currentInfo2);
		model3.setCurrentInfo(currentInfo3);
		
		assertEquals(model1.getCurrentInfo(), currentInfo);
		assertEquals(model2.getCurrentInfo(), currentInfo2);
		assertEquals(model3.getCurrentInfo(), currentInfo3); 
	}
	
	@Test
	public void testGetAndSetPendingInfo() {
		// Edit when database is finished 
		InfoState pendingInfo = new InfoState();
		InfoState pendingInfo2 = new InfoState();
		InfoState pendingInfo3 = new InfoState();
		model1.setPendingInfo(pendingInfo);
		model2.setPendingInfo(pendingInfo2);
		model3.setPendingInfo(pendingInfo3);
		
		assertEquals(model1.getPendingInfo(), pendingInfo);
		assertEquals(model2.getPendingInfo(), pendingInfo2);
		assertEquals(model3.getPendingInfo(), pendingInfo3); 
	}
	
	@Test
	public void testGetAndSetInfoStateComponent() {
		InfoState test = new InfoState();
		test.setNumContents(3);
		assertEquals(test.getNumContents(), 3);
		ContentComponent cmp1 = new ContentComponent("Hello");
		ContentComponent cmp2 = new ContentComponent("Wor");
		ContentComponent cmp3 = new ContentComponent("ld!");
		test.setContentAtIndex(0, cmp1);
		test.setContentAtIndex(1, cmp2);
		test.setContentAtIndex(2, cmp3);
		assertEquals(test.getContentAtIndex(0), cmp1);
		assertEquals(test.getContentAtIndex(1), cmp2);
		assertEquals(test.getContentAtIndex(2), cmp3);
	}
	
	@Test
	public void testStatusCalculation() {
		InfoState bad = new InfoState();
		bad.setNumContents(1);
		InfoState good = new InfoState();
		good.setNumContents(1);
		ContentComponent badContent = new ContentComponent("bad");
		ContentComponent goodContent = new ContentComponent("good");
		badContent.setStatus(false);
		goodContent.setStatus(true);
		bad.setContentAtIndex(0, badContent);
		good.setContentAtIndex(0, goodContent);
		model1.setCurrentInfo(good);
		model2.setCurrentInfo(bad);
		model1.calculateStatus();
		model2.calculateStatus();
		assertTrue(model1.getStatus());
		assertTrue(!model2.getStatus());
	}
}
