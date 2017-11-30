package com.bancolombia.AuthenticSoporte.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BusinessException(String mensaje){
		super(MessageFormat.format("Error en proceso de negocio: {0} ",mensaje));
	}

}
