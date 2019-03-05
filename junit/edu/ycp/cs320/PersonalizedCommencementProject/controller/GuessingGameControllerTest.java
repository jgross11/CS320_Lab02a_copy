package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.GuessingGameController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.GuessingGame;

public class GuessingGameControllerTest {
	private GuessingGame model;
	private GuessingGameController controller;

	@Before
	public void setUp() {
		model = new GuessingGame();
		controller = new GuessingGameController();
		controller.setModel(model);
		controller.startGame();
	}

	@Test
	public void testNumberIsGreater() {
		int currentGuess = model.getGuess();
		controller.setNumberIsGreaterThanGuess();
		assertTrue(model.getGuess() > currentGuess);
	}

	@Test
	public void testSetNumberFound() {
		int currentGuess = model.getGuess();
		controller.setNumberFound();
		assertTrue(currentGuess == model.getMax());
		assertTrue(currentGuess == model.getMin());
	}

	@Test
	public void testNumberIsLess() {
		int currentGuess = model.getGuess();
		controller.setNumberIsLessThanGuess();
		assertTrue(model.getGuess() < currentGuess);
	}
}
