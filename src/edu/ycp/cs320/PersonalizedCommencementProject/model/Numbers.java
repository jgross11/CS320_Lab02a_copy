package edu.ycp.cs320.PersonalizedCommencementProject.model;

public class Numbers {
	private double result, a, b, c;

	public Numbers() {

	}

	public void setNum(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public void add() {
		result = a + b + c;
	}

	public void multiply() {
		result = a * b;
	}

	public double getResult() {
		return result;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}
}
