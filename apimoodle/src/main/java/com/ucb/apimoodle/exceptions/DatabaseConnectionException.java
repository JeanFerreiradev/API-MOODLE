package com.ucb.apimoodle.exceptions;

// EXECEÇÃO PARA ERRPS DE CPMEXÃO COM BANDO DE DADOS
@SuppressWarnings("serial")
public class DatabaseConnectionException extends RuntimeException {
	public DatabaseConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
