package com.bancolombia.AuthenticSoporte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.AuthenticSoporte.Business.IMonitoreoBusiness;
import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;
import com.bancolombia.AuthenticSoporte.Exception.ErrorRest;
import com.bancolombia.AuthenticSoporte.dao.IMonitoreoDao;

@RestController
public class MonitoreoController {
	
	private static final String errorProcesoNegocio = "Error en el proceso de Negocio";

	@Autowired
	@Qualifier("monitoreoBusiness")
	IMonitoreoBusiness monitoreo;

	@GetMapping("/monitoreo")
	public ResponseEntity<?> obtenerMonitoreo(){
		List<Monitoreo> resultado = null;
		try{
			resultado = monitoreo.generarInformeMonitoreo();
		}catch (BusinessException e) {
			return new ResponseEntity<ErrorRest>(new ErrorRest(errorProcesoNegocio),HttpStatus.BAD_REQUEST);	
		}
		return new ResponseEntity<List<Monitoreo>>(resultado,HttpStatus.FOUND);
	}

}
