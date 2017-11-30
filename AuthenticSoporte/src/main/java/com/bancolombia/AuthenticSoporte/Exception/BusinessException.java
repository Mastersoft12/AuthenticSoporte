package com.bancolombia.AuthenticSoporte.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BusinessException(String mensaje){
		super(String.format("Error en proceso de negocio: "+mensaje+" "));
	}

}
