package edu.ycp.cs320.lab02a_jgross11.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NumbersTest {
	private Numbers model1, model2;
	
	@Before
	public void setUp() {
		model1 = new Numbers();
		model2 = new Numbers();
		model1.setNum(1.0, 2.0, 3.0);
		model2.setNum(3.0, 6.0, 9.0);
	}
	
	@Test
	public void testSetNum_AND_getNums() {
		assertTrue(model1.getA() == 1.0);
		assertTrue(model2.getA() == 3.0);
		assertTrue(model1.getB() == 2.0);
		assertTrue(model2.getB() == 6.0);
		assertTrue(model1.getC() == 3.0);
		assertTrue(model2.getC() == 9.0);
	}
	@Test
	public void testAdd() {
		model1.add();
		model2.add();
		assertTrue(model1.getResult() == 6.0);
		assertTrue(model2.getResult() == 18.0);
	}
	@Test
	public void testMultiply() {
		model1.multiply();
		model2.multiply();
		assertTrue(model1.getResult() == 2.0);
		assertTrue(model2.getResult() == 18.0);
	}
}
