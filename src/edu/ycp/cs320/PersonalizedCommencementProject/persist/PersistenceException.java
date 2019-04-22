package edu.ycp.cs320.PersonalizedCommencementProject.persist;

/*
 * This file is heavily based off of example code provided by <DONALD J. HAKE II>
 * Thanks.
 */

public class PersistenceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PersistenceException(String msg) {
		super(msg);
	}
	
	public PersistenceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
