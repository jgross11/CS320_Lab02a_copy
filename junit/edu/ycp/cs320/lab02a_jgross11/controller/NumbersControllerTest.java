package edu.ycp.cs320.lab02a_jgross11.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02a_jgross11.controller.NumbersController;
import edu.ycp.cs320.lab02a_jgross11.model.Numbers;

public class NumbersControllerTest {
	private NumbersController controller;
	private Numbers model;
	
	@Before
	public void setup() {
		controller = new NumbersController();
		model = new Numbers();
		controller.setModel(model);
	}
	
	@Test
	public void testAdd() {
		assertTrue(controller.add(1.0, 2.0, 3.0) == 6.0);
		assertTrue(controller.add(1.0, 2.0, 4.0) != 6.0);
	}
	@Test
	public void testMultiply() {
		assertTrue(controller.multiply(1.0, 2.0) == 2.0);
		assertTrue(controller.multiply(1.0, 3.0) != 2.0);
	}
}
