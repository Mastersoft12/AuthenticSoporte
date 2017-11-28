package com.bancolombia.AuthenticSoporte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;
import com.bancolombia.AuthenticSoporte.dao.IMonitoreoDao;

@RestController
public class MonitoreoController {
	

	@Autowired
	@Qualifier("monitoreoDao")
	IMonitoreoDao monitoreoDao;

	
	@GetMapping("/monitoreo")
	public List<Monitoreo> obtenerMonitoreo()throws BusinessException{	
		List<Monitoreo> resultado = monitoreoDao.obtenerMonitoreo();
	return resultado;	
	}
	
	
}
