package com.ucb.apimoodle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// CLASSE INTERNA PARA ESTRUTURA DA RESPOSTA DE ERRO
	public static class ErrorResponse {
		private int status;
		private String message;
		private long timeStamp;
		
		public ErrorResponse(int status, String message) {
			this.status = status;
			this.message = message;
			this.timeStamp = System.currentTimeMillis();
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public long getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}
		
	}
	
	// MANIPULA ResourceNotFoundException E RETORNA 404
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// MANIPULA DatabaseConnectionException E RETORNA 503
	@ExceptionHandler(DatabaseConnectionException.class)
	public ResponseEntity<ErrorResponse> handleDatabaseConnection(DatabaseConnectionException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), "Serviço indisponível. Tente novamente mais tarde.");
		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	// MANIPULA InternalServerException E RETORNA 500
	@ExceptionHandler(InternalServerErrorExeception.class)
	public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerErrorExeception ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor.");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// MANIPULA OUTRAS EXECEÇÕES GENÉRICAS
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro inesperado.");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
