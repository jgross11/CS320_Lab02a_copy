package edu.ycp.cs320.PersonalizedCommencementProject.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.PersonalizedCommencementProject.controller.ZUNUSED_GuessingGameController;
import edu.ycp.cs320.PersonalizedCommencementProject.model.ZUNUSED_GuessingGame;

public class ZUNUSED_GuessingGameControllerTest {
	private ZUNUSED_GuessingGame model;
	private ZUNUSED_GuessingGameController controller;

	@Before
	public void setUp() {
		model = new ZUNUSED_GuessingGame();
		controller = new ZUNUSED_GuessingGameController();
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
