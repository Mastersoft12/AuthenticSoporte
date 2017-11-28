package com.bancolombia.AuthenticSoporte.dao;

import java.util.List;

import com.bancolombia.AuthenticSoporte.Entity.Monitoreo;
import com.bancolombia.AuthenticSoporte.Exception.BusinessException;

public interface IMonitoreoDao {
	
	public List<Monitoreo> obtenerMonitoreoAprobado() throws BusinessException;
	
	public List<Monitoreo> obtenerMonitoreoRechazado() throws BusinessException;

}
