package com.ucb.apimoodle.exceptions;

// EXECEÇÃO PARA QUANDO UM RECURSO NÃO É ENCONTRADO
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
