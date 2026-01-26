package com.uisrael.hikvision.backend.dominio.excepciones;

public class DominioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DominioException(String message) {
		super(message);
	}
}
