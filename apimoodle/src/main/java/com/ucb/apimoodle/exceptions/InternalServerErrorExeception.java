package com.ucb.apimoodle.exceptions;

@SuppressWarnings("serial")
public class InternalServerErrorExeception extends RuntimeException {
	public InternalServerErrorExeception(String message) {
		super(message);
	}
}
