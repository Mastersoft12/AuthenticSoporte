package com.bancolombia.AuthenticSoporte.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Enum.MonitoreoEnum;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;
import com.bancolombia.AuthenticSoporte.dao.IMonitoreoDao;


@Component("monitoreoBusiness")
public class MonitoreoBusiness implements IMonitoreoBusiness {
	
	@Autowired
	@Qualifier("monitoreoDao")
	IMonitoreoDao monitoreoDao;
	
	private Predicate<List<Monitoreo>> predicate = s-> s.isEmpty();
	
	public List<Monitoreo> generarInformeMonitoreo() throws BusinessException{
		List<Monitoreo> resultado = new ArrayList<>();		
		resultado.addAll(monitoreoDao.obtenerMonitoreoAprobado());
		resultado.addAll(monitoreoDao.obtenerMonitoreoRechazado());	
		validarResultado(resultado);
		return resultado;		
	}
	
	private void validarResultado(List<Monitoreo> resultado){
		if(predicate.test(resultado)){
			resultado.add(obtenerMonitoreoPorDefecto(MonitoreoEnum.APROBADO.getNombre()));	
			resultado.add(obtenerMonitoreoPorDefecto(MonitoreoEnum.RECHAZADA.getNombre()));	
		}else{
			valorporDefecto(resultado);

		}
	}
	
	private void valorporDefecto(List<Monitoreo> resultado){
		if(resultado.stream().filter(x -> x.getDescripcion().equalsIgnoreCase(MonitoreoEnum.APROBADO.getNombre())).count() <= 0){
			resultado.add(obtenerMonitoreoPorDefecto(MonitoreoEnum.APROBADO.getNombre()));
		}
		if(resultado.stream().filter(x -> x.getDescripcion().equalsIgnoreCase(MonitoreoEnum.RECHAZADA.getNombre())).count() <= 0){
			resultado.add(obtenerMonitoreoPorDefecto(MonitoreoEnum.RECHAZADA.getNombre()));
		}
	}
	
	private Monitoreo obtenerMonitoreoPorDefecto(String tipoMonitoreo){
		return new Monitoreo(0, tipoMonitoreo, 0);
	}

}
