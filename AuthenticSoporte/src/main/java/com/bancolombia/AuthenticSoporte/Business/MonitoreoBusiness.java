package com.bancolombia.AuthenticSoporte.Business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;
import com.bancolombia.AuthenticSoporte.dao.IMonitoreoDao;


@Component("monitoreoBusiness")
public class MonitoreoBusiness implements IMonitoreoBusiness {
	
	@Autowired
	@Qualifier("monitoreoDao")
	IMonitoreoDao monitoreoDao;
	
	public List<Monitoreo> generarInformeMonitoreo() throws BusinessException{
		List<Monitoreo> resultado = new ArrayList<>();		
		resultado.addAll(monitoreoDao.obtenerMonitoreoAprobado());
		resultado.addAll(monitoreoDao.obtenerMonitoreoRechazado());		
		return resultado;
		
	}

}
