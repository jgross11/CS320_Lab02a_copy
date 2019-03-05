package edu.ycp.cs320.PersonalizedCommencementProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.model.GuessingGame;

public class GuessingGameTest {
	private GuessingGame model;

	@Before
	public void setUp() {
		model = new GuessingGame();
	}

	@Test
	public void testSetMin() {
		model.setMin(1);
		assertEquals(1, model.getMin());
	}

	@Test
	public void testSetMax() {
		model.setMax(100);
		assertEquals(100, model.getMax());
	}

	@Test
	public void testGetMin() {
		model.setMin(1);
		assertTrue(model.getMin() == 1);
	}

	@Test
	public void testGetMax() {
		model.setMax(100);
		assertTrue(model.getMax() == 100);
	}

	@Test
	public void testIsDone() {
		model.setMax(100);
		model.setMin(1);
		assertTrue(!model.isDone());
		model.setMin(100);
		assertTrue(model.isDone());
	}

	@Test
	public void testGetGuess() {
		model.setMin(1);
		model.setMax(9);
		assertTrue(model.getGuess() == 5);
		model.setMax(11);
		assertTrue(model.getGuess() == 6);
	}

	@Test
	public void testSetIsLessThan() {
		model.setMax(100);
		model.setIsLessThan(11);
		assertTrue(model.getMax() == 10);
	}

	@Test
	public void testSetIsGreaterThan() {
		model.setMin(13);
		model.setIsGreaterThan(54);
		assertTrue(model.getMin() == 55);
	}
}
